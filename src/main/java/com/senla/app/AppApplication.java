package com.senla.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
	private final static Logger logger = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		logger.info("Application start");
		SpringApplication.run(AppApplication.class, args);
	}
}
