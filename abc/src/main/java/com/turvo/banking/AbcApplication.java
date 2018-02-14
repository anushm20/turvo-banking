package com.turvo.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.turvo.banking.branch.services.ServiceCounterListener;
import com.turvo.banking.branch.token.services.CustomerTokenHelper;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.turvo.banking")
public class AbcApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context = 
				SpringApplication.run(AbcApplication.class, args);
	/*	CustomerTokenHelper helper = new CustomerTokenHelper();
		ServiceCounterListener listener = new ServiceCounterListener(helper);
		helper.addObserver(listener);*/
	}
}
