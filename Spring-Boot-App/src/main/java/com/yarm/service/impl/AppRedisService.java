package com.yarm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yarm.redis.service.RedisService;

@Service("appRedisService")
public class AppRedisService {

	@Autowired
	private RedisService redisService;
	
	@Value("${yarm.redis.time.out}")
	private Long redisTimeOut;
	
	public String getRedis(String key) throws Exception {
		
		return redisService.getRedis(key);
	}
	
	public boolean setRedis(String key, String value) throws Exception {
		
		return redisService.setRedis(key, value, redisTimeOut);
	}
	
	public boolean updateRedis(String key, String value) throws Exception {
		
		return redisService.updateRedis(key, value, redisTimeOut);
	}
}
