package com.mijael.CSSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@EnableJpaRepositories(considerNestedRepositories = true)
@SpringBootApplication
public class CsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsSpringApplication.class, args);
		System.out.println("IOContainer was loaded");
	}

}
