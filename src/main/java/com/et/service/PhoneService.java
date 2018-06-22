/**
 * 
 */
package com.et.service;

import java.util.List;
import java.util.Map;

import com.et.entity.Phone;

/**
 * @author Qixiaohao
 * @version 1.0
 * 
 */
public interface PhoneService {
	public int getPhoneCount(Map phone);

	public List queryPhoneAll(Integer page, Integer limit, Map phone);

	public void savePhone(Phone phone);

	public Map getPhoneByType(String type);

	public Map detailPhone(String type);
}
