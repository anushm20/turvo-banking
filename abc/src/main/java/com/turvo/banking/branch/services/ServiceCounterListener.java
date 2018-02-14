/**
 * Listener to update queues
 * For each customer token created this listener will be 
 * updated with the created the tokne
 * 
 * This will take care of keeping the token in queues at respective 
 * service counters
 */
package com.turvo.banking.branch.services;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.operations.PremiumServiceCounter;
import com.turvo.banking.branch.counter.operations.RegularServiceCounter;
import com.turvo.banking.branch.counter.operations.ServiceCounterPicker;
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
		ServiceCounterPicker picker = new ServiceCounterPicker();
		if(CustomerType.PREMIUM.toString().equalsIgnoreCase(token.getCustomerType())) {
			picker.setServiceCounterType(ApplicationContextProvider.
					getApplicationContext().getBean("premiumServiceCounter",
							PremiumServiceCounter.class));
		} else {
			// Regular Customer
			picker.setServiceCounterType(ApplicationContextProvider.getApplicationContext().
					getBean("regularServiceCounter",RegularServiceCounter.class));
		}
		picker.updateQueue(token);
	}

}
