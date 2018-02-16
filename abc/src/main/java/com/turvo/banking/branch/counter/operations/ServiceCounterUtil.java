/**
 * Utility class for Service Counter Operations 
 */
package com.turvo.banking.branch.counter.operations;

import java.util.Objects;
import java.util.PriorityQueue;

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
			// Create a Priority Queue
			PriorityQueue<CustomerToken> queue = new PriorityQueue<>();
			queue.add(token);
			counter.setTokenQueue(queue);
		}
	}
}
