/**
 * 
 */
package com.turvo.banking.customer.entities;

import java.util.List;

/**
 * @author anushm
 *
 */
public class Customer {
	
	private Long customerId;
	private String name;
	private CustomerType type;
	private List<CustomerAddress> addresses;
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
