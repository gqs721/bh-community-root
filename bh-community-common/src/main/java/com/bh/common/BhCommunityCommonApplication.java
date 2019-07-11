package com.bh.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

public class BhCommunityCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BhCommunityCommonApplication.class, args);
	}


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BhCommunityCommonApplication.class);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}
