/**
 * All the counter variables which has to be reset everyday
 * will be stored with this entity
 */
package com.turvo.banking.branch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="count")
@NamedQueries({
	@NamedQuery(name="Count.findByBranchAndName", query="from Count where "
			+ "branchId=:branchId and name=:name")
})
public class Count {
	
	@Column(name="branch_id")
	private Integer branchId;
	
	@Id
	@Column(name="name")
	private String name;
	
	@Column(name="count")
	private Integer number;
	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
