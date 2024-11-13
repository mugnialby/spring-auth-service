package com.alby.spring_auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class})
public class SpringAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAuthServiceApplication.class, args);
	}

}
