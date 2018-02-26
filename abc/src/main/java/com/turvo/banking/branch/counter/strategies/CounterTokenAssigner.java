/**
 * Listener to update queues
 * For each customer token created this listener will be 
 * updated with the created the token
 * 
 * This will take care of keeping the token in queues at respective 
 * service counters
 */
package com.turvo.banking.branch.counter.strategies;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.operations.CountersUtil;
import com.turvo.banking.branch.exceptions.BankEntityNotFoundException;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.services.TokenService;
import com.turvo.banking.common.BankingConstants;

/**
 * @author anushm
 *
 */
@Component
public class CounterTokenAssigner {
	
	@Autowired
	CountersUtil util;
	
	@Autowired
	private TokenService tokenService;
	
	@RabbitListener(queues=BankingConstants.CREATED_TOKEN_QUEUE)
	@Transactional
	public void update(Long tokenId) {
		if(Objects.nonNull(tokenId)) {
			try {
				Token token = tokenService.getTokenById(tokenId);
				updateTokeninQueues(token);
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
	/**
	 * helper method to update the token into queues
	 * @param token
	 * @throws BankEntityNotFoundException 
	 */
	public void updateTokeninQueues(Token token) throws BankEntityNotFoundException {
		// Get the branch from the token
		if (token.getBranchId() != null) {
			// Based on branch strategy pick strategy
			try {
				CounterStrategyPicker counterType = util.getStrategyPickerForBranch(token.getBranchId());
				boolean queued = false;
				queued = counterType.queueTokenAtFirstCounter(token);
				if (!queued) {
					// Re try Mechanism should go here
					// Can be moved to Dead letter queue
				}
			} catch (InvalidDataException e) {
				e.printStackTrace();
				// LOG the statement
				// Inform the branch
			}
		}
	}

}
