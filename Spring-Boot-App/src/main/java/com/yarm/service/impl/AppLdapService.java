package com.yarm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yarm.base.pojo.SsoUser;
import com.yarm.ldap.service.MyLdapBeanService;

@Service
public class AppLdapService {

	@Autowired
	private MyLdapBeanService myLdapBeanService;
	
	public SsoUser getUserInfoById(String id) {
		
		return this.myLdapBeanService.getUserInfo(id);
	}
}
