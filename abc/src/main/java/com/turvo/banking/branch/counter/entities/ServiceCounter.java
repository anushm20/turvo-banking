/**
 * Service Counter entity
 */
package com.turvo.banking.branch.counter.entities;

import java.util.PriorityQueue;

import com.turvo.banking.branch.token.entities.CustomerToken;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
public class ServiceCounter {
	
	@ApiModelProperty(notes = "Database Generated ID for Service Counter")
	private Long serviceCounterId;
	@ApiModelProperty(notes = "Service which Service counter can serve",required=true)
	private Long serviceId;
	@ApiModelProperty(notes = "Type of the Service Counter", required = true)
	private CounterType counterType;
	@ApiModelProperty(notes = "Priority Queue to manage the tokens in the counter")
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

	public CounterType getCounterType() {
		return counterType;
	}

	public void setCounterType(CounterType counterType) {
		this.counterType = counterType;
	}

	public PriorityQueue<CustomerToken> getTokenQueue() {
		return tokenQueue;
	}

	public void setTokenQueue(PriorityQueue<CustomerToken> tokenQueue) {
		this.tokenQueue = tokenQueue;
	}
	
}
