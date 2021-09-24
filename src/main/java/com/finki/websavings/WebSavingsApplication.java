package com.finki.websavings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WebSavingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSavingsApplication.class, args);
	}

}
