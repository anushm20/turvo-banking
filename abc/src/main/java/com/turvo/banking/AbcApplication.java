package com.turvo.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.turvo.banking.branch.counter.operations.ServiceCounterListener;
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
		ServiceCounterListener listener = new ServiceCounterListener(helper);
		helper.addObserver(listener);
		return helper;
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory  =
	    			new JedisConnectionFactory();
	    jedisConFactory.setHostName("127.0.0.1");
	    jedisConFactory.setPort(6379);
	    return jedisConFactory;
	}
	
}
