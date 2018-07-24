package com.yarm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yarm.service.impl.AppUserService;

@RestController
@RequestMapping(value="user")
public class UserController {

	@Autowired
	private AppUserService appUserService;
	
	/**
	 * 查询用户列表
	 * @return
	 */
	@RequestMapping(value = "list.ctrip", method = RequestMethod.GET)
	public Map<String, Object> getRedisJson(){
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("status", 200);
			map.put("data", this.appUserService.selectUser());
		} catch (Exception e) {
			map.put("status", 500);
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
}
