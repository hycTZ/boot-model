package com.yarm.ldap.service.impl;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import com.yarm.base.pojo.SsoUser;
import com.yarm.ldap.service.MyLdapBeanService;

@Component
public class MyLdapBeanServiceImpl implements MyLdapBeanService {

    private final LdapTemplate ldapTemplate;
    @Autowired
    public MyLdapBeanServiceImpl(LdapTemplate template) {
        this.ldapTemplate = template;
    }
    
    @Value(value = "${ad.ldap.user.dn}")
    private String ldapUserDn;
    
	@Override
	public SsoUser getUserInfo(String userId) {
        String filter = "(cn=" + userId + ")";
        // 从ldap中获取用户信息
        List<SsoUser> userList = ldapTemplate.search(ldapUserDn, filter, new AttributesMapper<SsoUser>() {

                @Override
                public SsoUser mapFromAttributes(Attributes attributes) throws NamingException {
                	SsoUser user = new SsoUser();

                        Attribute attribute = attributes.get("sn");// 姓名
                        if (null != attribute)
                        	user.setUserName((String) (attribute.get()));

                        attribute = attributes.get("cn");// 工号
                        if (null != attribute)
                        	user.setUserId((String) (attribute.get()));

                        attribute = attributes.get("distinguishedName");// dn
                        if (null != attribute)
                        	user.setDistinguishedName((String) (attribute.get()));

                        return user;
                }
        });
        if (userList.isEmpty()) {
			return null;
		}
        return userList.get(0);
	}
}
