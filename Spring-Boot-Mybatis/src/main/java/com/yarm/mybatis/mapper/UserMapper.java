package com.yarm.mybatis.mapper;

import java.util.List;

import com.yarm.base.pojo.User;

public interface UserMapper {

	/**
	 * 新增一条用户
	 * @return
	 */
	public List<User> insertUser();
	
	/**
	 * 删除一条数据
	 * @param id
	 * @return
	 */
	public List<User> deleteUserById(int id);
	
	/**
	 * 通过ID修改一条用户数据
	 * @param id
	 * @return
	 */
	public List<User> updateUserById(int id);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<User> selectUser();
	
}
