package com.yarm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yarm.service.impl.RedisServiceImpl;

@RestController
@RequestMapping(value="redis")
public class RedisController {

	@Autowired
	private RedisServiceImpl redisService;
	
	@RequestMapping(value = "{id}.json", method = RequestMethod.GET)
	public Map<String, Object> getRedisJson(@PathVariable("id") String id){
		Map<String, Object> map = new HashMap<>();
		try {
			String redis = redisService.getRedis(id);
			map.put("status", 200);
			map.put("data", redis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "{id}.ctr", method = RequestMethod.GET)
	public Map<String, Object> getRedisXml(@PathVariable("id") String id){
		Map<String, Object> map = new HashMap<>();
		try {
			String redis = redisService.getRedis(id);
			map.put("status", 200);
			map.put("data", redis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "{key}_{value}.ctr", method = RequestMethod.GET)
	public Map<String, Object> setRedis(@PathVariable("key") String key, @PathVariable("value") String value){
		Map<String, Object> map = new HashMap<>();
		try {
			 redisService.setRedis(key, value);
			map.put("status", 200);
			map.put("data", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@RequestMapping(value = "set.ctr", method = RequestMethod.POST)
	public Map<String, Object> setRedisMap(@RequestBody Map<String,Object> reqMap){
		Map<String, Object> map = new HashMap<>();
		try {
			String key = reqMap.get("key").toString();
			String value = reqMap.get("value").toString();
			
			map.put("status", 200);
			map.put("data", this.redisService.setRedis(key, value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@RequestMapping(value = "update.ctr", method = RequestMethod.PUT)
	public Map<String, Object> updateRedisMap(@RequestBody Map<String,Object> reqMap){
		Map<String, Object> map = new HashMap<>();
		try {
			String key = reqMap.get("key").toString();
			String value = reqMap.get("value").toString();
			
			map.put("status", 200);
			map.put("data", this.redisService.updateRedis(key, value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
