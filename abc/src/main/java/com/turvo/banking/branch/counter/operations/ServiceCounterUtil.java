/**
 * Utility class for Service Counter Operations 
 */
package com.turvo.banking.branch.counter.operations;

import java.util.Objects;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class ServiceCounterUtil {
	
	public static void updateServiceCounterQueue(CustomerToken token, ServiceCounter counter) {
		// Add token to queue
		if(Objects.nonNull(counter.getTokenQueue())) {
			counter.getTokenQueue().add(token);
		} else {
			// This should not get executed for in memory database
			// First create the queue
			// Then add the element
		}
	}
}
