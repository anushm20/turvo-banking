package com.turvo.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.turvo.banking")
public class AbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcApplication.class, args);
	}
	
	/*@Bean
	public  TokenHelper customTokenHelper() {
		TokenHelper helper = new TokenHelper();
		CounterTokenAssigner listener = new CounterTokenAssigner(helper);
		helper.addObserver(listener);
		return helper;
	}*/
	
}
