/**
 * Service Counter entity
 */
package com.turvo.banking.branch.counter.entities;

import java.util.PriorityQueue;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class ServiceCounter {
	
	private Long serviceCounterId;
	private Long serviceId;
	private ServiceCounterType counterType;
	private PriorityQueue<CustomerToken> tokenQueue;
	
	public Long getServiceCounterId() {
		return serviceCounterId;
	}

	public void setServiceCounterId(Long serviceCounterId) {
		this.serviceCounterId = serviceCounterId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceCounterType getCounterType() {
		return counterType;
	}

	public void setCounterType(ServiceCounterType counterType) {
		this.counterType = counterType;
	}

	public PriorityQueue<CustomerToken> getTokenQueue() {
		return tokenQueue;
	}

	public void setTokenQueue(PriorityQueue<CustomerToken> tokenQueue) {
		this.tokenQueue = tokenQueue;
	}
	
}
