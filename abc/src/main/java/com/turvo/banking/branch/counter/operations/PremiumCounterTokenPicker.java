/**
 * Implementation class for Premimum Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.counter.services.BranchCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Component("PREMIUMServiceCounter")
public class PremiumCounterTokenPicker implements BranchCounterTokenPicker {
	
	@Autowired
	BranchCounterService counterServices;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.ServiceCounterType#updateServiceCounterQueue(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateServiceCounterQueue(CustomerToken token) {
		List<Long> counters = counterServices.getPremiumServiceCounters();
		if(Objects.nonNull(counters) && counters.size() > 0) {
			// Get Priority Service Counter
			// Get the first one for now and assign the token
			BranchCounter counter = counterServices.getServiceCounterById
								(counters.get(0));
			BranchCounterUtil.updateServiceCounterQueue
			(token, counter);
			// Save it to database
			counterServices.updateServiceCounter(counter);
		}
	}

}
