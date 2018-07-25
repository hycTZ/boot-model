package com.yarm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yarm.base.pojo.SsoUser;
import com.yarm.service.impl.AppLdapService;

@RestController
@RequestMapping(value="ldap", consumes = "application/json", produces = "application/json")
public class LdapController {

	@Autowired
	private AppLdapService appLdapService;
	
	@RequestMapping(value = "user_info.ctr", method = RequestMethod.POST)
	public Map<String, Object> getUserInfoByLdap(@RequestBody Map<String, Object> reMap){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			SsoUser user = this.appLdapService.getUserInfoById(reMap.get("id").toString());
			map.put("code", 200);
			map.put("data", user);
		} catch (Exception e) {
			
			map.put("code", 500);
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
}
