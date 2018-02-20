/**
 * Interface for Service counter type to update queue
 */
package com.turvo.banking.branch.counter.operations;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
public interface BranchCounterTokenPicker {
	
	/**
	 * Update queue of respective service counter
	 * @param token
	 */
	public void updateServiceCounterQueue(Token token);

}
