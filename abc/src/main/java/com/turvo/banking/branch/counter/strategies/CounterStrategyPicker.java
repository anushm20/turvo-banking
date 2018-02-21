/**
 * Interface for counter stratgies in a branch
 * and update the token into respective queues
 */
package com.turvo.banking.branch.counter.strategies;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
public interface CounterStrategyPicker {
	
	/**
	 * Update counter queues based on the services
	 * 	 selected and customer type
	 * @param token
	 */
	public boolean updateCounterQueue(Token token);

}
