/**
 * Implementation class for Premimum Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
@Component("premiumServiceCounter")
public class PremiumCounterTokenPicker implements BranchCounterTokenPicker {
	
	@Autowired
	CounterService counterServices;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.ServiceCounterType#updateServiceCounterQueue(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateServiceCounterQueue(Token token) {
		List<Long> counters = counterServices.getCountersByType(CounterType.PREMIUM);
		if(Objects.nonNull(counters) && counters.size() > 0) {
			// Get Priority Service Counter
			// Get the first one for now and assign the token
			Counter counter = counterServices.getCounterById
								(counters.get(0));
			BranchCounterUtil.INSTANCE.updateServiceCounterQueue
			(token, counter);
			// Save it to database
			counterServices.updateCounter(counter);
		}
	}

}
