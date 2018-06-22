/**
 * 
 */
package com.et.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.et.entity.Phone;
import com.et.entity.ResponseEnt;
import com.et.service.PhoneService;

/**
 * @author Qixiaohao
 * @version 1.0
 * 
 */
@RestController
public class PhoneController {
	@Autowired
	PhoneService phoneService;

	@GetMapping("/phone/queryPhoneAll")
	public ResponseEnt queryPhone(Integer page, Integer limit, String keyword, String price1, String price2) {

		Map phone = new HashMap();
		phone.put("keyword", keyword);
		phone.put("price1", price1);
		phone.put("price2", price2);
		int count = phoneService.getPhoneCount(phone);
		List<Map> phones = phoneService.queryPhoneAll(page, limit, phone);
		ResponseEnt re = new ResponseEnt();
		re.setCount(count);
		re.setData(phones);
		return re;
	}

	@RequestMapping("/phone/addPhone")
	public String addUser(String type, String brand, String price, String color, String size, String date,
			String remark) {
		Phone phone = new Phone(type, brand, price, color, size, Date.valueOf(date), remark);
		Map addPhone = phoneService.getPhoneByType(type);
		if (addPhone.size() == 0) {
			phoneService.savePhone(phone);
			return "1";
		} else {
			return "0";
		}
	}

	@GetMapping("/phone/detailPhone")
	public Map detailUser(String type) {
		return phoneService.detailPhone(type);

	}

}
