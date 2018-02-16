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

import com.turvo.banking.branch.counter.services.BranchCounterMappingServices;
import com.turvo.banking.branch.counter.services.BranchCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.services.CustomerTokenHelper;
import com.turvo.banking.common.ApplicationContextProvider;

/**
 * @author anushm
 *
 */
@Component
public class BranchCounterTokenAssigner implements Observer {
	
	@Autowired
	BranchCounterService counterServices;
	
	@Autowired
	BranchCounterMappingServices branchCounterMappingServices;
	
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
			CustomerToken token = (CustomerToken)arg;
			updateTokeninQueues(token);
		}

	}
	
	public void updateTokeninQueues(CustomerToken token) {
		// Based on Customer Type respective service counter 
		// will be picked automatically
		if(token.getCustomerType() != null && !token.getCustomerType().isEmpty()) {
			BranchCounterTokenPicker  counterType = (BranchCounterTokenPicker)ApplicationContextProvider.
					getApplicationContext().getBean(token.getCustomerType()+"ServiceCounter");
			counterType.updateServiceCounterQueue(token);
		}
	}

}
