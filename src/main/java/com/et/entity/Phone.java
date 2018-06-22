package com.et.entity;

import java.sql.Date;

public class Phone {
	private String p_type;// 手机型号
	private String p_brand;// 手机品牌
	private String p_price;// 手机价格
	private String p_color;// 手机颜色
	private String p_size;// 手机尺寸
	private Date p_date;// 手机生产日期
	private String p_remark;// 手机描述

	public Phone() {
	}

	public Phone(String p_type, String p_brand, String p_price, String p_color, String p_size, Date p_date,
			String p_remark) {
		super();
		this.p_type = p_type;
		this.p_brand = p_brand;
		this.p_price = p_price;
		this.p_color = p_color;
		this.p_size = p_size;
		this.p_date = p_date;
		this.p_remark = p_remark;
	}

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public String getP_brand() {
		return p_brand;
	}

	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}

	public String getP_price() {
		return p_price;
	}

	public void setP_price(String p_price) {
		this.p_price = p_price;
	}

	public String getP_color() {
		return p_color;
	}

	public void setP_color(String p_color) {
		this.p_color = p_color;
	}

	public String getP_size() {
		return p_size;
	}

	public void setP_size(String p_size) {
		this.p_size = p_size;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public String getP_remark() {
		return p_remark;
	}

	public void setP_remark(String p_remark) {
		this.p_remark = p_remark;
	}

	@Override
	public String toString() {
		return "Phone [p_type=" + p_type + ", p_brand=" + p_brand + ", p_price=" + p_price + ", p_color=" + p_color
				+ ", p_size=" + p_size + ", p_date=" + p_date + ", p_remark=" + p_remark + "]";
	}

}
