package com.yarm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yarm.base.pojo.User;
import com.yarm.user.service.UserService;

@Service
public class AppUserService {

	@Autowired
	private UserService userService;
	
	public List<User> selectUser(){
		return this.userService.selectUser();
	}
}
