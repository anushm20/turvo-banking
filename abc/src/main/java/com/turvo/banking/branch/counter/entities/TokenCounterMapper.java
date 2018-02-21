/**
 * Token Counter Mapper entity 
 *  for a token all the counters which customer has to 
 *  go will be stored with the order
 */
package com.turvo.banking.branch.counter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.turvo.banking.branch.token.entities.Token;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="token_counter_mapper")
public class TokenCounterMapper {
	
	@Id
	@GeneratedValue(generator = "counterQueueId")
	@GenericGenerator(
			name="counterQueueId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="CounterQueueImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.counter.entities.CounterQueue")
			}
			)
	@Column(name="counter_queue_id")
	@ApiModelProperty(notes = "Database Generated ID for Counter Queue")
	private Long counterQueueId;
	
	@ManyToOne
	@JsonBackReference(value="token-reference")
	@JoinColumn(name="token_id")
	@ApiModelProperty(notes = "Token ID")
	private Token token;

	@ManyToOne
	@JsonBackReference(value="counter-reference")
	@JoinColumn(name="counter_id")
	@ApiModelProperty(notes = "Counter ID")
	private Counter counter;
	
	@Column(name="token_counter_order")
	@ApiModelProperty(notes = "Order of the counter in "
			+ "which customer has to go")
	private Integer order;
	
	public TokenCounterMapper() {
	}
	
	public TokenCounterMapper(Token token, Counter counter, Integer order){
		this.token = token;
		this.counter = counter;
		this.order = order;
	}

	public Long getCounterQueueId() {
		return counterQueueId;
	}

	public void setCounterQueueId(Long counterQueueId) {
		this.counterQueueId = counterQueueId;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounterId(Counter counter) {
		this.counter = counter;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
}
