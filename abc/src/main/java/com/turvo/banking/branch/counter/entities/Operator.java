/**
 *  Operator of a bank
 */
package com.turvo.banking.branch.counter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.turvo.banking.branch.entities.Branch;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="operator")
public class Operator {

	@Id
	@GeneratedValue(generator = "operatorId")
	@GenericGenerator(
			name="operatorId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="OperatorImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.counter.entities.Operator")
			}
			)
	@Column(name="operator_id")
	@ApiModelProperty(notes = "Unique ID assigned to the operator")
	private Long operatorId;
	
	@Column(name="name")
	@ApiModelProperty(notes = "Name of the Operator")
	private String name;
	
	@OneToOne
	@JoinColumn(name="branch_id")
	@ApiModelProperty(notes = "Branch in which operator working")
	private Branch branch;

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
