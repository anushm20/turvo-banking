/**
 *  Listener class for Token Counters exchange queue
 *  
 */
package com.turvo.banking.branch.counter.strategies;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.operations.CountersUtil;
import com.turvo.banking.branch.exceptions.BankEntityNotFoundException;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.entities.TokenExchange;
import com.turvo.banking.branch.token.services.TokenService;
import com.turvo.banking.common.BankingConstants;

/**
 * @author anushm
 *
 */
@Component
public class CounterTokenProcessor {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	CountersUtil util;
	
	@RabbitListener(queues=BankingConstants.TOKEN_COUNTER_EXCHANGE_QUEUE)
	@Transactional
	public void processTokenToNextStage(TokenExchange exchange) {
		if(Objects.nonNull(exchange)) {
			try {
				Token token = tokenService.getTokenById(exchange.getTokenId());
				processToken(token,exchange.getCounterId());
			} catch (BankEntityNotFoundException e) {
				e.printStackTrace();
				// LOG the statement
			} catch (Exception e) {
				e.printStackTrace();
				// Add them to dead letter queue
			}
		} else {
			// Handle exception
		}
	}
	
	public void processToken(Token token, Long counterId) throws BankEntityNotFoundException {
		if (token.getBranchId() != null) {
			// Based on branch strategy pick strategy
			CounterStrategyPicker counterType = util.getStrategyPickerForBranch(token.getBranchId());
			boolean process = false;
			process = counterType.processTokenToNextStages(token, counterId);
			if (!process) {
				// Re try Mechanism should go here
				// Can be moved to Dead letter queue
			}
		}
	}

}
