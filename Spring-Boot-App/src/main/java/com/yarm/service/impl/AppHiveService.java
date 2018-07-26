package com.yarm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yarm.base.pojo.CompanyInfoPojo;
import com.yarm.hadoop.hive.service.HiveService;

@Service
public class AppHiveService {

	@Autowired
	private HiveService hiveService;
	
	public List<CompanyInfoPojo> getHiveData() {
		return this.hiveService.getHiveData();
	}
}
