/**
 * 
 */
package com.et.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import com.et.entity.Phone;

/**
 * @author Qixiaohao
 * @version 1.0
 * 
 */

@Mapper
@Repository
public interface PhoneMapper {

	/**
	 * 内部类
	 * 
	 * @author Qixiaohao
	 * @version 1.0
	 *
	 */
	static class SqlProvider {
		public String queryPhoneAll(Map params) {
			Map param = (Map) params.get("phone");
			Object keyword = param.get("keyword");
			Object price1 = param.get("price1");
			Object price2 = param.get("price2");
			Integer starIndex = (Integer) params.get("starIndex");
			Integer limit = (Integer) params.get("limit");
			SQL sql = new SQL();
			sql = sql.SELECT("*").FROM("phone");
			if (keyword != null && !"".equals(keyword)) {
				sql.WHERE("concat(p_id,p_type,p_brand,p_price,p_color,p_size,p_date) like '%" + keyword + "%'");
			}
			if (price1 != null && !"".equals(price1)) {
				sql.AND();
				sql.WHERE("p_price >= '" + price1 + "'");
			}
			if (price2 != null && !"".equals(price2)) {
				sql.AND();
				sql.WHERE("p_price <= '" + price2 + "'");
			}
			String sqlStr = sql.toString() + " limit " + starIndex + " , " + limit;

			return sqlStr;

		}

		public String getPhoneCountSql(Map params) {
			Map param = (Map) params.get("phone");
			Object keyword = param.get("keyword");
			Object brand = param.get("brand");
			Object price1 = param.get("price1");
			Object price2 = param.get("price2");
			SQL sql = new SQL();
			sql = sql.SELECT("count(*)").FROM("phone");
			if (keyword != null && !"".equals(keyword)) {
				sql.WHERE("concat(p_id,p_type,p_brand,p_price,p_color,p_size,p_date) like '%" + keyword + "%'");
			}
			if (brand != null && !"".equals(brand)) {
				sql.AND();
				sql.WHERE("p_brand = '" + brand + "'");
			}
			if (price1 != null && !"".equals(price1)) {
				sql.AND();
				sql.WHERE("p_price >= " + price1);
			}
			if (price2 != null && !"".equals(price2)) {
				sql.AND();
				sql.WHERE("p_price <= " + price2);
			}
			return sql.toString();
		}

		public String getPhoneByType(Map params) {
			Object type = params.get("type");
			SQL sql = new SQL();
			sql = sql.SELECT("*").FROM("phone").WHERE("p_type='" + type + "'");
			return sql.toString();
		}

	}

	@SelectProvider(type = SqlProvider.class, method = "getPhoneCountSql")
	public int getPhoneCount(@Param("phone") Map params);

	@SelectProvider(type = SqlProvider.class, method = "queryPhoneAll")
	public List<Map> queryPhoneAll(@Param("starIndex") Integer starIndex, @Param("limit") Integer limit,
			@Param("phone") Map params);

	@Insert("insert into phone(p_type,p_brand,p_price,p_color,p_size,p_date,p_remark) values(#{phone.p_type},#{phone.p_brand},${phone.p_price},#{phone.p_color},#{phone.p_size},#{phone.p_date},#{phone.p_remark})")
	public void savePhone(@Param("phone") Phone phone);

	@SelectProvider(type = SqlProvider.class, method = "getPhoneByType")
	public Map<String, String> getPhoneByType(@Param("type") String type);

}
