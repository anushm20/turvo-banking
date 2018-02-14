/**
 * Class to pick correct ServiceCounterType to update queue
 */
package com.turvo.banking.branch.counter.operations;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class ServiceCounterPicker {
	private ServiceCounterType serviceCounterType;
	
	public void setServiceCounterType(ServiceCounterType type) {
		this.serviceCounterType = type;
	}
	
	public void updateQueue(CustomerToken token) {
		serviceCounterType.updateServiceCounterQueue(token);
	}
}
