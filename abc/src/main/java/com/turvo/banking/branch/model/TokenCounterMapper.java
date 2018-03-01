/**
 * Token Counter Mapper entity 
 *  for a token all the counters which customer has to 
 *  go will be stored with the order
 */
package com.turvo.banking.branch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="token_counter_mapper")
@NamedQueries({
	@NamedQuery(name="TokenCounterMapper.getNextToken",query="select map.token.tokenId from TokenCounterMapper map"
		+ " where map.counter.counterId=:counterId"),
	@NamedQuery(name="Counter.findMinTokensCounter", query="from TokenCounterMapper map " + 
			"where map.counter.counterId in :counterIdList " + 
			"group by map.counter.counterId,map.counterQueueId order by count(map.token.tokenId) asc")
	
})
public class TokenCounterMapper implements Serializable{
	
	/**
	 * Default Serial Verison ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "counterQueueId")
	@GenericGenerator(
			name="counterQueueId",
			strategy="com.turvo.banking.common.OverrideTableIdGenerator",
			parameters = {
					@Parameter(name="segment_value", value="CounterQueueImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.counter.entities.CounterQueue")
			}
			)
	@Column(name="mapper_id")
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
	
	public TokenCounterMapper() {
	}
	
	public TokenCounterMapper(Token token, Counter counter){
		this.token = token;
		this.counter = counter;
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

}
