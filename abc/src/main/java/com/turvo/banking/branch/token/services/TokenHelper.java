/**
 * Helper class to notify listeners that a new 
 * customer token has been created
 */
package com.turvo.banking.branch.token.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.token.entities.TokenExchange;

/**
 * @author anushm
 *
 */
@Component
public class TokenHelper  {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	/**
	 * Method to send a token to designated queue
	 * @param tokenId
	 * @param Queue
	 */
	public void sendToken(Long tokenId,String queue) {
		this.rabbitTemplate.convertAndSend(queue,tokenId);
	}
	
	
	public void sendTokenToProcess(String queue, Long tokenId, Long counterId) {
		this.rabbitTemplate.convertAndSend(queue,new TokenExchange(tokenId,counterId));
	}
}