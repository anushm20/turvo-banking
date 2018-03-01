/**
 * Service Counter entity
 */
package com.turvo.banking.branch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="counter")
@NamedQueries({
	@NamedQuery(name="Counter.findByServiceAndType", query="from Counter where "
			+ "brServiceId=:brServiceId and counterType=:type"),
	@NamedQuery(name="Counter.findTokenNumbersByCounter", query="select t.number from Token t JOIN t.counter c"
			+ "	where c.counterId=:counterId"),
	@NamedQuery(name="Counter.findMinTokensCounter", query="select c.counterId from Token t JOIN t.counter c " + 
			"where c.counterId in :counterIdList " + 
			"group by c.counterId order by count(t.tokenId) asc"),
	@NamedQuery(name="Counter.getNextToken",query="select t from Token t JOIN t.counter c "
			+ " where c.counterId=:counterId order by t.priority desc")
})
public class Counter implements Serializable {
	
	/**
	 * Default Serial Verison ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "counterId")
	@GenericGenerator(
			name="counterId",
			strategy="com.turvo.banking.common.OverrideTableIdGenerator",
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
	
	/*@OneToMany(cascade=CascadeType.ALL,mappedBy="counter",orphanRemoval=true)
	@JsonManagedReference(value="counter-reference")
	@ApiModelProperty(notes = "List of tokens which can be served in this counter")
	private List<TokenCounterMapper> queuedTokens;*/
	
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

/*	public List<TokenCounterMapper> getQueuedTokens() {
		return queuedTokens;
	}

	public void setQueuedTokens(List<TokenCounterMapper> queuedTokens) {
		this.queuedTokens = queuedTokens;
	}*/

}