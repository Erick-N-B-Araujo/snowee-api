package com.snoweegamecorp.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter
public class DBConfiguration {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	@Profile("dev")
	@Bean
	public String testDatabaseConnection() {
		System.out.print("DB connection for DEV - H2");
		System.out.print(driverClassName);
		System.out.print(url);
		return "DB Connection to H2_TEST - Test instance";
	}
	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		System.out.print("DB connection for PROD - MYSQL");
		System.out.print(driverClassName);
		System.out.print(url);
		return "DB Connection to MYSQL_PROD - Test instance";
	}
}
