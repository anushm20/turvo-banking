/**
 * Customer Token Entity
 */
package com.turvo.banking.branch.token.entities;

import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
public class CustomerToken {
	
	@ApiModelProperty(notes = "Token Number(Generated internally)")
	private Long number;
	@ApiModelProperty(notes = "Customer ID who came for the services",required=true)
	private Long customerId ;
	@ApiModelProperty(notes = "Type of the customer(Premium/Non-Premium",required=true)
	private String customerType;
	@ApiModelProperty(notes = "Notes that can be added at service counter(s)")
	private String comments;
	@ApiModelProperty(notes = "Current Status of the token ")
	private TokenStatus status;
	@ApiModelProperty(notes = "List of services opted by customer")
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
