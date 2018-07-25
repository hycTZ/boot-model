package com.yarm.ldap.service;

import com.yarm.base.pojo.SsoUser;

public interface MyLdapBeanService {

	/**
	 * 查询人员信息
	 * @param userId
	 * @return
	 */
	public SsoUser getUserInfo(String userId);
}
