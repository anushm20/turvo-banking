/**
 * 
 */
package com.turvo.banking.branch.token.entities;

import java.util.List;

/**
 * @author anushm
 *
 */
public class CustomerToken {
	
	private Long customerId;
	private Long number;
	private int priority;
	private String comments;
	private TokenStatus status;
	private List<Long> services;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public TokenStatus getStatus() {
		return status;
	}
	public void setStatus(TokenStatus status) {
		this.status = status;
	}
	public List<Long> getServices() {
		return services;
	}
	public void setServices(List<Long> services) {
		this.services = services;
	}
	
}
