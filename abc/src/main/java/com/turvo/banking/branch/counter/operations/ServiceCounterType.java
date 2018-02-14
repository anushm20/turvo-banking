/**
 * Interface for Service counter type to update queue
 */
package com.turvo.banking.branch.counter.operations;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface ServiceCounterType {
	
	/**
	 * Update queue of respective service counter
	 * @param token
	 */
	public void updateServiceCounterQueue(CustomerToken token);

}
