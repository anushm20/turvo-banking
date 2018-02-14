/**
 * Listener to update queues
 * For each customer token created this listener will be 
 * updated with the created the tokne
 * 
 * This will take care of keeping the token in queues at respective 
 * service counters
 */
package com.turvo.banking.branch.services;

import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.counter.services.ServiceCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.services.CustomerTokenHelper;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
@Component
public class ServiceCounterListener implements Observer {
	
	@Autowired
	ServiceCounterService counterServices;
	
	@Autowired
	BranchServices branchServices;
	
	private CustomerTokenHelper helper = null;
	
	public ServiceCounterListener() {
	}
	
	public ServiceCounterListener(CustomerTokenHelper helper) {
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
		// Premium Customer
			if(CustomerType.PREMIUM.toString().equalsIgnoreCase(token.getCustomerType())) {
				List<Long> counters = counterServices.getPremiumServiceCounters();
				if(Objects.nonNull(counters) && counters.size() > 0) {
					// Get Priority Service Counter
					// Get the first one for now and assign the token
					updateServiceCounterQueue(token, counters);
				}
			} else {
				// Regular Customer
				List<Long> services = token.getServices();
				for (Long serviceId : services) {
					// Get Service Counters for the services
					List<Long> counters = branchServices.getServiceCountersForService(serviceId);
					updateServiceCounterQueue(token, counters);
				}
			}
	}

	private void updateServiceCounterQueue(CustomerToken token, List<Long> counters) {
		//TODO Load balancing strategy for each counter
		ServiceCounter counter = counterServices.getServiceCounterById(counters.get(0));
		// Add token to queue
		if(Objects.nonNull(counter.getTokenQueue())) {
			counter.getTokenQueue().add(token);
		} else {
			// This should not get executed for in memory database
			// First create the queue
			// Then add the element
		}
	}
	
}
