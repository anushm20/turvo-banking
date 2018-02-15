/**
 * Customer Entity
 */
package com.turvo.banking.customer.entities;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
public class Customer {
	@ApiModelProperty(notes = "ID generated internally and used for"
			+ "future communications")
	private Long customerId;
	@ApiModelProperty(notes = "Name of the customer",required=true)
	private String name;
	@ApiModelProperty(notes = "Type of the customer", required=true)
	private CustomerType type;
	@ApiModelProperty(notes = "Addresses of the customer(atleast one)",required=true)
	private List<CustomerAddress> addresses;
	@ApiModelProperty(notes = "Phone Number of the customer",required=true)
	private long phoneNumber;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CustomerType getType() {
		return type;
	}
	public void setType(CustomerType type) {
		this.type = type;
	}
	public List<CustomerAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<CustomerAddress> addresses) {
		this.addresses = addresses;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
