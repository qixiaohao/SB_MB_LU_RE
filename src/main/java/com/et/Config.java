/**
 * 
 */
package com.et;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author Qixiaohao
 * @version 1.0
 * 
 */
@Configuration
public class Config {

	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String drive;

	@Bean
	public DataSource myConfig() {
		DruidDataSource dds = new DruidDataSource();
		dds.setUsername(username);
		dds.setPassword(password);
		dds.setDriverClassName(drive);
		dds.setUrl(url);
		return dds;
	}

}
