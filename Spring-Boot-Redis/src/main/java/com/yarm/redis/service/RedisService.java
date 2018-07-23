package com.yarm.redis.service;

public interface RedisService {

	/**
	 * 插入一条数据
	 * @param key
	 * @param value
	 * @param timeOut
	 * @throws Exception
	 */
	public boolean setRedis(String key, String value, Long timeOut) throws Exception;

	/**
	 * 获取一条数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getRedis(String key) throws Exception;
	
	/**
	 * 更新key
	 * @param key
	 * @param value
	 * @param timeOut
	 * @throws Exception
	 */
	public boolean updateRedis(String key, String value, Long timeOut) throws Exception;
	
}
