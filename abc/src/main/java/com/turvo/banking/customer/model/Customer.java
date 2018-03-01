/**
 * Customer Entity
 */
package com.turvo.banking.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable{
	
	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "customerId")
	@GenericGenerator(
			name="customerId",
			strategy="com.turvo.banking.common.OverrideTableIdGenerator",
			parameters = {
					@Parameter(name="segment_value", value="CustomerServiceImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.customer.entities.Customer")
			}
			)
	@Column(name="customer_id")
	@ApiModelProperty(notes = "ID generated internally and used for"
			+ "future communications")
	private Long customerId;
	
	@NotNull
	@Column(name="name")
	@ApiModelProperty(notes = "Name of the customer",required=true)
	private String name;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	@ApiModelProperty(notes = "Type of the customer", required=true)
	private CustomerType type;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
	        name = "customer_address_mapping",
	        joinColumns = @JoinColumn(name = "customer_id"),
	        inverseJoinColumns = @JoinColumn(name = "address_id")
	)
	@ApiModelProperty(notes = "Addresses of the customer(atleast one)",required=true)
	private Set<Address> addresses;
	
	@NotNull
	@Column(name="phone_number")
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
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
