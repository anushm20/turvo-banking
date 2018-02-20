/**
 * Listener to update queues
 * For each customer token created this listener will be 
 * updated with the created the tokne
 * 
 * This will take care of keeping the token in queues at respective 
 * service counters
 */
package com.turvo.banking.branch.counter.operations;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.services.CustomerTokenHelper;

/**
 * @author anushm
 *
 */
@Component
public class BranchCounterTokenAssigner implements Observer {
	
	@Autowired
	CounterService counterServices;
	
	private CustomerTokenHelper helper = null;
	
	public BranchCounterTokenAssigner() {
	}
	
	public BranchCounterTokenAssigner(CustomerTokenHelper helper) {
		this.helper = helper;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(Objects.nonNull(arg)) {
			Token token = (Token)arg;
			updateTokeninQueues(token);
		}

	}
	
	public void updateTokeninQueues(Token token) {
		// Based on Customer Type respective service counter 
		// will be picked automatically
		if(token.getCustomer().getType() != null && !token.getCustomer().getType().toString().isEmpty()) {
			BranchCounterTokenPicker counterType;
			try {
				counterType = CounterTokenAssignerFactory.
							getTokenPicker(token.getCustomer().getType().toString());
				counterType.updateServiceCounterQueue(token);
			} catch (CounterStrategyNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
