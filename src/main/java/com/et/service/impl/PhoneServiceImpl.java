/**
 * 
 */
package com.et.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.et.entity.Phone;
import com.et.mapper.PhoneMapper;
import com.et.service.PhoneService;

/**
 * @author Qixiaohao
 * @version 1.0
 * 
 */
@Service
public class PhoneServiceImpl implements PhoneService {
	@Autowired
	PhoneMapper phoneMapper;
	@Autowired
	StringRedisTemplate template;

	@Override
	public int getPhoneCount(Map phone) {
		Object keyword = phone.get("keyword");
		Object price1 = phone.get("price1");
		Object price2 = phone.get("price2");
		if (keyword == null) {
			keyword = "";
			phone.put("keyword", keyword);
		}
		if (price1 == null) {
			price1 = "";
			phone.put("price1", price1);
		}
		if (price2 == null) {
			price2 = "";
			phone.put("price2", price2);
		}
		int count = phoneMapper.getPhoneCount(phone);
		return count;
	}

	@Override
	public List queryPhoneAll(Integer page, Integer limit, Map phone) {
		Object keyword = phone.get("keyword");
		Object price1 = phone.get("price1");
		Object price2 = phone.get("price2");
		if (keyword == null) {
			keyword = "";
			phone.put("keyword", keyword);
		}
		if (price1 == null) {
			price1 = "";
			phone.put("price1", price1);
		}
		if (price2 == null) {
			price2 = "";
			phone.put("price2", price2);
		}
		int starIndex = (page - 1) * limit;
		List<Map> phones = phoneMapper.queryPhoneAll(starIndex, limit, phone);
		return phones;
	}

	@Override
	public void savePhone(Phone phone) {
		phoneMapper.savePhone(phone);
	}

	@Override
	public Map getPhoneByType(String type) {
		return phoneMapper.getPhoneByType(type);
	}

	@Override
	public Map detailPhone(String type) {

		String phoneType = "phone:" + type;
		// 查询缓存里面有没有对应的数据
		Set<String> phone = template.keys("*");
		if (phone.size() > 0) {
			// 查询到了直接返回数据
			return template.boundHashOps(phoneType).entries();
		} else {
			// 没有先从数据库查询出来然后存到缓存
			Map map = phoneMapper.getPhoneByType(type);
			String id = map.get("p_id") + "";
			map.put("p_id", id);
			Date date = (Date) map.get("p_date");
			String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			map.put("p_date", dateStr);
			template.boundHashOps(phoneType).putAll(map);
			return map;
		}
	}

}
