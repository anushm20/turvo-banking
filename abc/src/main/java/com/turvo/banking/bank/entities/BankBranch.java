/**
 * Bank Branch Entity
 */
package com.turvo.banking.bank.entities;

/**
 * @author anushm
 *
 */
public class BankBranch {
	
	private Bank bank;
	private Long branchCode;
	private BankAddress branchAddress;
	
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public Long getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Long branchCode) {
		this.branchCode = branchCode;
	}
	public BankAddress getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(BankAddress branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	

}
