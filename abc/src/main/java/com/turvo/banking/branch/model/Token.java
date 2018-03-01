/**
 * Customer Token Entity
 */
package com.turvo.banking.branch.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.turvo.banking.customer.model.Customer;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="token")
@NamedQueries({
	@NamedQuery(name="Token.findTokenBasedOnPriority", query="from Token where "
			+ "tokenId in :tokenIds order by priority desc"),
	@NamedQuery(name="Token.findTokenByNumber", query="from Token where "
			+ "number=:number")
})
public class Token implements Serializable{
	
	/**
	 * Default Serial version ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="token_id")
	@GeneratedValue(generator = "tokenId")
	@GenericGenerator(
			name="tokenId",
			strategy="com.turvo.banking.common.OverrideTableIdGenerator",
			parameters = {
					@Parameter(name="segment_value", value="TokenImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.token.entities.Token")
			}
			)
	@ApiModelProperty(notes = "Primary Key of the table")
	private Long tokenId;
	
	@Column(name="branch_id")
	@ApiModelProperty(notes = "Branch in which the token generated")
	private Integer branchId;

	@Column(name="number")
	@ApiModelProperty(notes = "Token Number(Generated internally)")
	private Integer number;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="customer_id")
	@ApiModelProperty(notes = "Customer ID who came for the services",required=true)
	private Customer customer ;

	@Column(name="priority")
	@ApiModelProperty(notes = "Priority assigned to the token",required=true)
	private int priority;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	@ApiModelProperty(notes = "Current Status of the token ")
	private TokenStatus status;
	
	@NotNull
	@ElementCollection
	@Column(name="branch_service_id")
	@CollectionTable(
	        name = "token_service",
	        joinColumns = @JoinColumn(name = "token_id")
	)
	@ApiModelProperty(notes = "List of services opted by customer")
	private List<Long> branchServices;
	
	@OneToOne
	@JoinTable(name="token_counter",
					joinColumns=@JoinColumn(name="token_id"),
					inverseJoinColumns=@JoinColumn(name="counter_id"))
	private Counter counter;
	
	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public TokenStatus getStatus() {
		return status;
	}

	public void setStatus(TokenStatus status) {
		this.status = status;
	}

	public List<Long> getBranchServices() {
		return branchServices;
	}

	public void setBranchServices(List<Long> branchServices) {
		this.branchServices = branchServices;
	}
	
	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		
		if(!(obj instanceof Token)) return false;
		
		Token token = (Token) obj;
		return this.number == token.getNumber();
	}
	
}
