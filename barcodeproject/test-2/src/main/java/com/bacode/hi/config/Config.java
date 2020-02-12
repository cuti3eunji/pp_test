package com.bacode.hi.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Config { //데이터베이스 연결

	
	@ConfigurationProperties(prefix = "spring.datasource") public DataSource
	dataSource() { return DataSourceBuilder.create().build();
	
	}
	

}
