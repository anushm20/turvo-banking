/**
 * Customer Address Entity
 */
package com.turvo.banking.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(generator = "addressId")
	@GenericGenerator(
			name="addressId",
			strategy="com.turvo.banking.common.OverrideTableIdGenerator",
			parameters = {
					@Parameter(name="segment_value", value="AddressImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.customer.entities.Address")
			}
			)
	@Column(name="address_id")
	@ApiModelProperty(notes = "Primary key for the table")
	private Long addressId;

	@Enumerated(EnumType.STRING)
	@Column(name="type")
	@ApiModelProperty(notes = "Address type(communication/permanent)")
	private AddressType addressType;

	@Column(name="address_line1")
	@ApiModelProperty(notes = "Address of the customer")
	private String addressLine1;

	@Column(name="address_line2")
	@ApiModelProperty(notes = "Address of the customer")
	private String addressLine2;

	@Column(name="postal_code")
	@ApiModelProperty(notes = "Postal code of the customer")
	private String postalCode;

	@Column(name="city")
	@ApiModelProperty(notes = "City of the customer")
	private String city;

	@Column(name="state")
	@ApiModelProperty(notes = "State of the customer")
	private String state;

	@Column(name="country")
	@ApiModelProperty(notes = "Country of the customer")
	private String country;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
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
