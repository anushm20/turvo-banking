/**
 * Implementation class for regular Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.counter.services.BranchCounterMappingServices;
import com.turvo.banking.branch.counter.services.BranchCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Component("REGULARServiceCounter")
public class RegularCounterTokenPicker implements BranchCounterTokenPicker {

	@Autowired
	BranchCounterMappingServices branchCounterMappingServices;
	
	@Autowired
	BranchCounterService counterServices;
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.ServiceCounterType#updateServiceCounterQueue(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateServiceCounterQueue(CustomerToken token) {
		List<Long> services = token.getServices();
		for (Long serviceId : services) {
			// Get Service Counters for the services
			List<Long> counters = branchCounterMappingServices.getServiceCountersForService(serviceId);
			BranchCounter counter = counterServices.getServiceCounterById
					(counters.get(0));
			BranchCounterUtil.updateServiceCounterQueue
				(token, counter);
			counterServices.updateServiceCounter(counter);
		}
	}

}
