package com.yarm.redis.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yarm.redis.service.RedisService;

@Service
@SuppressWarnings("rawtypes")
public class RedisServiceImpl implements RedisService{

	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean setRedis(String key, String value, Long timeOut) throws Exception{
		boolean result = false;
		ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
		if (!this.redisTemplate.hasKey(key)) {
			ops.set(key, value, timeOut, TimeUnit.SECONDS);
			result = true;
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String getRedis(String key) throws Exception{
		ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
		return ops.get(key);
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean updateRedis(String key, String value, Long timeOut) throws Exception {
		boolean result = false;
		if (this.redisTemplate.hasKey(key)) {
			ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
			ops.set(key, value, timeOut, TimeUnit.SECONDS);
			result = true;
		}
		return result;
	}

}
