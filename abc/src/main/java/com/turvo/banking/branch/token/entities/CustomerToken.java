/**
 * Customer Token Entity
 */
package com.turvo.banking.branch.token.entities;

import java.util.List;
import java.util.Objects;

/**
 * @author anushm
 *
 */
public class CustomerToken {
	
	private Long number;
	private Long customerId ;
	private String customerType;
	private String comments;
	private TokenStatus status;
	private List<Long> services;
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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
	
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		
		if(!(obj instanceof CustomerToken)) return false;
		
		CustomerToken token = (CustomerToken) obj;
		return this.number == token.getNumber();
	}
	
}
