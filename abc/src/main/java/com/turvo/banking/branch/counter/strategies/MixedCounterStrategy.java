/**
 * Implementation class for Mixed Counter Stratgery
 */
package com.turvo.banking.branch.counter.strategies;

import org.springframework.stereotype.Component;

import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.common.BankingConstants;

/**
 * @author anushm
 *
 */
@Component(BankingConstants.MIXED_STRATEGY)
public class MixedCounterStrategy implements CounterStrategyPicker {

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.CounterStrategyPicker#
	 * updateServiceCounterQueue(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public boolean updateCounterQueue(Token token) {
		// Get services list
		// Get counters associated to services and place them in the list
		// For a multicounter service , consider the order of counters
		// Try to load balance them 
		// Assign the token to first counter
		return true;
	}

}
