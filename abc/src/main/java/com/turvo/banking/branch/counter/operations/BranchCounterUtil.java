/**
 * Utility class for Service Counter Operations 
 */
package com.turvo.banking.branch.counter.operations;

import java.util.Objects;
import java.util.PriorityQueue;

import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class BranchCounterUtil {
	
	public static void updateServiceCounterQueue(CustomerToken token, BranchCounter counter) {
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
