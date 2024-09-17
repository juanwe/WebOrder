package com.weborder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class WebOrderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebOrderApplication.class, args);
	}
	
}
