package com.turvo.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.turvo.banking")
@Import(SwaggerConfig.class)
public class AbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcApplication.class, args);
	}
	
}
