/**
 * Implementation class for regular Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.counter.services.ServiceCounterService;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Component("REGULARServiceCounter")
public class RegularServiceCounter implements ServiceCounterType {

	@Autowired
	BranchServices branchServices;
	
	@Autowired
	ServiceCounterService counterServices;
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.ServiceCounterType#updateServiceCounterQueue(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateServiceCounterQueue(CustomerToken token) {
		List<Long> services = token.getServices();
		for (Long serviceId : services) {
			// Get Service Counters for the services
			List<Long> counters = branchServices.getServiceCountersForService(serviceId);
			ServiceCounter counter = counterServices.getServiceCounterById
					(counters.get(0));
			ServiceCounterUtil.updateServiceCounterQueue
				(token, counter);
			counterServices.updateServiceCounter(counter);
		}
	}

}
