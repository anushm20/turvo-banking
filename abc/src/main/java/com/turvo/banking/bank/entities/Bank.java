/**
 * Bank Entity
 */
package com.turvo.banking.bank.entities;

/**
 * @author anushm
 *
 */
public class Bank {
	private String Name;
	private BankAddress rootBankLocation;
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
