/**
 * Bank Entity
 */
package com.turvo.banking.bank.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.turvo.banking.customer.entities.Address;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="bank")
public class Bank {
	
	@Id
	@GeneratedValue(generator = "bankId")
	@GenericGenerator(
			name="bankId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="BankImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.bank.entities.Bank")
			}
			)
	@Column(name="bank_id")
	@ApiModelProperty(notes = "Unique Id given to the bank")
	private int bankId;
	
	@Column(name="name")
	@ApiModelProperty(notes = "Name of the bank")
	private String Name;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bank_address_id")
	@ApiModelProperty(notes = "Address of the Main branch of the bank")
	private Address rootBankLocation;
	
	@Column(name="bank_code")
	@ApiModelProperty(notes = "Unqiue code given to the bank")
	private int bankCode;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Address getRootBankLocation() {
		return rootBankLocation;
	}

	public void setRootBankLocation(Address rootBankLocation) {
		this.rootBankLocation = rootBankLocation;
	}

	public int getBankCode() {
		return bankCode;
	}

	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	
	

}
