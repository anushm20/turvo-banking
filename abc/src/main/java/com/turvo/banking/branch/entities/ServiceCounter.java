/**
 * 
 */
package com.turvo.banking.branch.entities;

import java.util.PriorityQueue;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class ServiceCounter {
	
	private Long serviceCounterId;
	
	private PriorityQueue<CustomerToken> tokenQueue;

	public Long getServiceCounterId() {
		return serviceCounterId;
	}

	public void setServiceCounterId(Long serviceCounterId) {
		this.serviceCounterId = serviceCounterId;
	}

	public PriorityQueue<CustomerToken> getTokenQueue() {
		return tokenQueue;
	}

	public void setTokenQueue(PriorityQueue<CustomerToken> tokenQueue) {
		this.tokenQueue = tokenQueue;
	}
	
}
