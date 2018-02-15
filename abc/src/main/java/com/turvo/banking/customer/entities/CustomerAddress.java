/**
 * Customer Address Entity
 */
package com.turvo.banking.customer.entities;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
public class CustomerAddress {

	@ApiModelProperty(notes = "Address type(communication/permanent)")
	private CustomerAddressType addressType;	
	@ApiModelProperty(notes = "Address of the customer")
	private String addressLine1;
	@ApiModelProperty(notes = "Address of the customer")
	private String addressLine2;
	@ApiModelProperty(notes = "Postal code of the customer")
	private String postalCode;
	@ApiModelProperty(notes = "City of the customer")
	private String city;
	@ApiModelProperty(notes = "State of the customer")
	private String state;
	@ApiModelProperty(notes = "Country of the customer")
	private String country;
	
	public CustomerAddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(CustomerAddressType addressType) {
		this.addressType = addressType;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
