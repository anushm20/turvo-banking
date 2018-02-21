/**
 * Implementation class for Premimum Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

import org.springframework.stereotype.Component;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
@Component("mixedCounterStrategy")
public class MixedCounterStrategy implements CounterStrategyPicker {

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.CounterStrategyPicker#
	 * updateServiceCounterQueue(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public void updateServiceCounterQueue(Token token) {
		// Get services list
		// Get counters associated to services and place them in the list
		// For a multicounter service , consider the order of counters
		// Try to load balance them 
		// Assign the token to first counter
	}

}
