/**
 * Implementation class for regular Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
@Component("regularServiceCounter")
public class RegularCounterTokenPicker implements BranchCounterTokenPicker {

	@Autowired
	CounterService counterServices;
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.ServiceCounterType#updateServiceCounterQueue(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateServiceCounterQueue(Token token) {
		List<Long> services = new ArrayList<>();
		for (Long serviceId : services) {
			// Get Service Counters for the services
			/*List<Long> counters = branchCounterMappingServices.getServiceCountersForService(serviceId);
			Counter counter = counterServices.getCounterById
					(counters.get(0));
			BranchCounterUtil.INSTANCE.updateServiceCounterQueue
				(token, counter);
			counterServices.updateCounter(counter);*/
		}
	}

}
