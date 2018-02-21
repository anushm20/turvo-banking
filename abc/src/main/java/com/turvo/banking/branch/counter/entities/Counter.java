/**
 * Service Counter entity
 */
package com.turvo.banking.branch.counter.entities;

import java.util.List;
import java.util.PriorityQueue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.turvo.banking.branch.token.entities.Token;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="counter")
public class Counter {
	
	@Id
	@GeneratedValue(generator = "counterId")
	@GenericGenerator(
			name="counterId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="CounterImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.counter.entities.Counter")
			}
			)
	@Column(name="counter_id")
	@ApiModelProperty(notes = "Database Generated ID for Service Counter")
	private Long counterId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="counter_type")
	@ApiModelProperty(notes = "Type of the Service Counter", required = true)
	private CounterType counterType;
	
	@Column(name="branch_service_id")
	@ApiModelProperty(notes = "Service which Service counter can serve",required=true)
	private Long brServiceId;
	
	@Column(name="multi_counter_order")
	@ApiModelProperty(notes = "For a Multicounter service, tells the"
			+ " order which it has to get executed",required=true)
	private int order;
	
	@Column(name="capacity")
	@ApiModelProperty(notes = "Maximum Number of "
			+ "customers it can serve",required=true)
	private int capacity;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="counter")
	@JsonManagedReference(value="counter-reference")
	@ApiModelProperty(notes = "List of tokens which can be served in this counter")
	private List<TokenCounterMapper> queuedTokens;
	
	@Transient
	@ApiModelProperty(notes = "Queue which holds the "
			+ "	List of tokens based on priority which can be served in this counter")
	private PriorityQueue<Token> counterQueue;

	public Long getCounterId() {
		return counterId;
	}

	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}

	public CounterType getCounterType() {
		return counterType;
	}

	public void setCounterType(CounterType counterType) {
		this.counterType = counterType;
	}

	public Long getBrServiceId() {
		return brServiceId;
	}

	public void setBrServiceId(Long brServiceId) {
		this.brServiceId = brServiceId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<TokenCounterMapper> getQueuedTokens() {
		return queuedTokens;
	}

	public void setQueuedTokens(List<TokenCounterMapper> queuedTokens) {
		this.queuedTokens = queuedTokens;
	}

	public PriorityQueue<Token> getCounterQueue() {
		return counterQueue;
	}

	public void setCounterQueue(PriorityQueue<Token> counterQueue) {
		this.counterQueue = counterQueue;
	}
}