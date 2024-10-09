package com.acrunetit.college.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollegeSwaggerConfiguration {
	
	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group("CollegeManagement")
	                .packagesToScan("com.acrunetit.college.controller") // Specify the package to scan
	                .build();
	 }

}
