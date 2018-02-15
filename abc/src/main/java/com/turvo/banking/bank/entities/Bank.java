/**
 * Bank Entity
 */
package com.turvo.banking.bank.entities;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
public class Bank {
	
	@ApiModelProperty(notes = "Name of the bank")
	private String Name;
	@ApiModelProperty(notes = "Address of the Main branch of the bank")
	private BankAddress rootBankLocation;
	@ApiModelProperty(notes = "Unqiue code given to the bank")
	private int bankCode;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public BankAddress getRootBankLocation() {
		return rootBankLocation;
	}
	public void setRootBankLocation(BankAddress rootBankLocation) {
		this.rootBankLocation = rootBankLocation;
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	
}
