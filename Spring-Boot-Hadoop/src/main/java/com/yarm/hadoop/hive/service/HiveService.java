package com.yarm.hadoop.hive.service;

import java.util.List;

import com.yarm.base.pojo.CompanyInfoPojo;

public interface HiveService {
	
	public List<CompanyInfoPojo> getHiveData();
}
