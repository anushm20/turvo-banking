/**
 * Services offered by a branch of a bank (among the list
 * of services available with bank)
 */
package com.turvo.banking.branch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.turvo.banking.bank.model.BankService;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="branch_service")
public class BranchService {
	
	@Id
	@GeneratedValue(generator = "branchServiceId")
	@GenericGenerator(
			name="branchServiceId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="BranchServiceImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.entities.BranchService")
			}
			)
	@Column(name="branch_service_id")
	@ApiModelProperty(notes = "The database generated Branch Service ID")
	private Long branchServiceId;

	@NotNull
	@Column(name="branch_id")
	@ApiModelProperty(notes = "Branch ID for which mapping is performed")
	private int branchId;

	@NotNull
	@ManyToOne
	@JoinColumn(name="service_id")
	@ApiModelProperty(notes = "Service ID of the bank mapped to the branch")
	private BankService service;

	@Column(name="is_multi_counter")
	@ApiModelProperty(notes = "Tells whether the service can "
			+ "be done at single counter or multipe counters")
	private boolean multiCounter;

	public Long getBranchServiceId() {
		return branchServiceId;
	}

	public void setBranchServiceId(Long branchServiceId) {
		this.branchServiceId = branchServiceId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public BankService getService() {
		return service;
	}

	public void setService(BankService service) {
		this.service = service;
	}

	public boolean getMultiCounter() {
		return multiCounter;
	}

	public void setMultiCounter(boolean multiCounter) {
		this.multiCounter = multiCounter;
	}
}
