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

import com.turvo.banking.RabbitConfig;
import com.turvo.banking.branch.entities.Branch;
import com.turvo.banking.branch.exceptions.EntityNotFoundException;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.services.BranchCrudService;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.services.TokenService;

/**
 * @author anushm
 *
 */
@Component
public class CounterTokenAssigner {
	
	@Autowired
	private BranchCrudService branchCrudService;
	
	@Autowired
	private TokenService tokenService;
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@RabbitListener(queues=RabbitConfig.TOKENS_QUEUE)
	@Transactional
	public void update(Long tokenId) {
		if(Objects.nonNull(tokenId)) {
			try {
				Token token = tokenService.getTokenById(tokenId);
				updateTokeninQueues(token);
			} catch (EntityNotFoundException e) {
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
	 * @throws EntityNotFoundException 
	 */
	public void updateTokeninQueues(Token token) throws EntityNotFoundException {
		// Get the branch from the token
		if(token.getBranchId() != null) {
			Branch branch = branchCrudService.getBranchById(token.getBranchId());
			if(Objects.nonNull(branch)) {
				// Based on branch strategy pick strategy
				CounterStrategyPicker counterType;
				try {
					counterType = CounterTokenAssignerFactory.
								getStrategyPicker(branch.getCounterStrategyType().toString());
					boolean queued = false;
					try {
						queued = counterType.updateCounterQueue(token);
					} catch (InvalidDataException e) {
						e.printStackTrace();
						// LOG the statement
						// Inform the branch
					}
					if(!queued) {
						// Re try Mechanism should go here
						// Can be moved to Dead letter queue
					}
				} catch (EntityNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				throw new EntityNotFoundException("Customer Selected Branch is not available");
			}
		}
	}

}
