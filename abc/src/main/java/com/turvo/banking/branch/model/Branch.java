/**
 * Bank Branch Entity
 */
package com.turvo.banking.branch.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.turvo.banking.bank.model.Bank;
import com.turvo.banking.customer.model.Address;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="branch")
public class Branch {
	
	@Id
	@GeneratedValue(generator = "branchId")
	@GenericGenerator(
			name="branchId",
			strategy="com.turvo.banking.common.OverrideTableIdGenerator",
			parameters = {
					@Parameter(name="segment_value", value="BranchImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.entities.Branch")
			}
			)
	@Column(name="branch_id")
	@ApiModelProperty(notes = "Primary key for the table")
	private int branchId;
	
	@OneToOne
	@JoinColumn(name="bank_id")
	@ApiModelProperty(notes = "Bank of this branch")
	private Bank bank;
	
	@Column(name="branch_code")
	@ApiModelProperty(notes = "Unqiue Branch Code")
	private int branchCode;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="branch_address_id")
	@ApiModelProperty(notes = "Address of this branch")
	private Address branchAddress;
	
	@Column(name="strategy_type")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Strategy type for Counter "
			+ "queues in the branch")
	private CounterStrategyType counterStrategyType;
	
	@ApiModelProperty(notes = "Frequency in which a priority customer has "
			+ "to be placed in mixed strategy")
	@Column(name="frequency")
	private int frequency;

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public int getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

	public Address getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(Address branchAddress) {
		this.branchAddress = branchAddress;
	}

	public CounterStrategyType getCounterStrategyType() {
		return counterStrategyType;
	}

	public void setCounterStrategyType(CounterStrategyType type) {
		this.counterStrategyType = type;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
