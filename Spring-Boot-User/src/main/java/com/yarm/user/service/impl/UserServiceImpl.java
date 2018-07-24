package com.yarm.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yarm.base.pojo.User;
import com.yarm.mybatis.mapper.UserMapper;
import com.yarm.user.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> selectUser() {
		return this.userMapper.selectUser();
	}
}
