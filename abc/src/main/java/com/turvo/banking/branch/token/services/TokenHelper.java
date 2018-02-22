/**
 * Helper class to notify listeners that a new 
 * customer token has been created
 */
package com.turvo.banking.branch.token.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.RabbitConfig;

/**
 * @author anushm
 *
 */
@Component
public class TokenHelper  {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendToken(Long tokenId) {
		this.rabbitTemplate.convertAndSend(RabbitConfig.TOKENS_QUEUE,tokenId);
	}
	
}