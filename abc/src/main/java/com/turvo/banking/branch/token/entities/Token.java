/**
 * Customer Token Entity
 */
package com.turvo.banking.branch.token.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.customer.entities.Customer;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="token")
public class Token implements Comparable<Token>{
	
	@Id
	@Column(name="token_id")
	@GeneratedValue(generator = "tokenId")
	@GenericGenerator(
			name="tokenId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="TokenImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.token.entities.Token")
			}
			)
	@ApiModelProperty(notes = "Primary Key of the table")
	private Long tokenId;

	@Column(name="number")
	@ApiModelProperty(notes = "Token Number(Generated internally)")
	private int number;
	
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
	private Set<Long> branchServices;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	        name = "counter_queue",
	        joinColumns = @JoinColumn(name = "token_id"),
	        inverseJoinColumns = @JoinColumn(name = "counter_id")
	)
	@ApiModelProperty(notes = "List of tokens which can be served in this counter")
	private List<Counter> counters;
	
	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public Set<Long> getBranchServices() {
		return branchServices;
	}

	public void setBranchServices(Set<Long> branchServices) {
		this.branchServices = branchServices;
	}

	public List<Counter> getCounters() {
		return counters;
	}

	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}

	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		
		if(!(obj instanceof Token)) return false;
		
		Token token = (Token) obj;
		return this.priority == token.getPriority();
	}
	
	@Override
	public int compareTo(Token token) {
		return Integer.compare(this.number, token.number);
	}
	
}
