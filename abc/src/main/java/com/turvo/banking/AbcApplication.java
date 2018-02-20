package com.turvo.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.turvo.banking.branch.counter.operations.BranchCounterTokenAssigner;
import com.turvo.banking.branch.token.services.CustomerTokenHelper;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.turvo.banking")
public class AbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcApplication.class, args);
	}
	
	@Bean
	public  CustomerTokenHelper customTokenHelper() {
		CustomerTokenHelper helper = new CustomerTokenHelper();
		BranchCounterTokenAssigner listener = new BranchCounterTokenAssigner(helper);
		helper.addObserver(listener);
		return helper;
	}
	
}
