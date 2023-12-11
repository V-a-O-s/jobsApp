package com.example.jobsApp;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JobsAppApplication {

	private static final Logger log = LoggerFactory.getLogger(JobsAppApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JobsAppApplication.class, args);

		Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(name -> log.info(name));
	}
}
