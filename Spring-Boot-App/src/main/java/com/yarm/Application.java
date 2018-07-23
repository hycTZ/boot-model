package com.yarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) //暂时不注入数据源
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
