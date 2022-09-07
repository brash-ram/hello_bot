package com.kpd.kpd_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KpdBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpdBotApplication.class, args);
	}

}