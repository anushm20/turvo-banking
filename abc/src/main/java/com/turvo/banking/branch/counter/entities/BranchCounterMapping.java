/**
 * Service to Service Counter Mapping in a Branch
 */
package com.turvo.banking.branch.counter.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@RedisHash("countermappings")
public class BranchCounterMapping {
	
	@Id
	@ApiModelProperty(notes = "Service ID of the bank", required=true)
	private Long serviceId;
	@ApiModelProperty(notes = "List of service counters which can "
			+ "		serve the service", required=true)
	private List<Long> serviceCounters;
	
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public List<Long> getServiceCounters() {
		return serviceCounters;
	}
	public void setServiceCounters(List<Long> serviceCounters) {
		this.serviceCounters = serviceCounters;
	}
	
}
