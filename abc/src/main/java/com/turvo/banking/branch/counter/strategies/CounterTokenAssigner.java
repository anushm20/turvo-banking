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
import java.util.Observable;
import java.util.Observer;

import org.springframework.stereotype.Component;

import com.turvo.banking.branch.entities.Branch;
import com.turvo.banking.branch.exceptions.EntityNotFoundException;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.services.BranchCrudService;
import com.turvo.banking.branch.services.BranchCrudServiceImpl;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.services.TokenHelper;
import com.turvo.banking.common.ApplicationContextProvider;

/**
 * @author anushm
 *
 */
@Component
public class CounterTokenAssigner implements Observer {
	
	private BranchCrudService branchCrudService;
	
	@SuppressWarnings("unused")
	private TokenHelper helper = null;
	
	public CounterTokenAssigner() {
	}
	
	public CounterTokenAssigner(TokenHelper helper) {
		this.helper = helper;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(Objects.nonNull(arg)) {
			Token token = (Token)arg;
			try {
				updateTokeninQueues(token);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
				// LOG the statement
			}
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
			branchCrudService = ApplicationContextProvider.getApplicationContext().
							getBean("branchCrudService",BranchCrudServiceImpl.class);
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
