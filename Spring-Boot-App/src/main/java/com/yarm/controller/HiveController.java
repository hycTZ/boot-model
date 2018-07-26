package com.yarm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yarm.base.pojo.CompanyInfoPojo;
import com.yarm.service.impl.AppHiveService;

@RestController
@RequestMapping(value="hive")
public class HiveController {

	@Autowired
	private AppHiveService hiveService;
	
	/**
	 * hive 建表用于测试
	 * @return
	 */
	@RequestMapping(value = "list.ctrip", method = RequestMethod.GET)
	public Map<String, Object> getRedisJson(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<CompanyInfoPojo> hiveData = this.hiveService.getHiveData();
			
			map.put("code", 200);
			map.put("data", hiveData);
		} catch (Exception e) {
			map.put("code", 500);
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
}
