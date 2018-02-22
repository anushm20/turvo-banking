/**
 * Customer Token Entity
 */
package com.turvo.banking.branch.token.entities;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.customer.entities.Customer;

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
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
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
	
	@OneToMany(mappedBy="token",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonManagedReference(value="token-reference")
	@ApiModelProperty(notes = "List of counters which customer has to go")
	private Set<TokenCounterMapper> counters;
	
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

	public Set<TokenCounterMapper> getCounters() {
		return counters;
	}

	public void setCounters(Set<TokenCounterMapper> counters) {
		this.counters.clear();
		this.counters.addAll(counters);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		
		if(!(obj instanceof Token)) return false;
		
		Token token = (Token) obj;
		return this.number == token.getNumber();
	}
	
}
