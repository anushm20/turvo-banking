/**
 * Sequence Entity
 */
package com.turvo.banking.common.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author anushm
 *
 */
@RedisHash("sequences")
public class Sequences {
	
	@Id
	private String entityName;
	private Long sequence;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	

}
