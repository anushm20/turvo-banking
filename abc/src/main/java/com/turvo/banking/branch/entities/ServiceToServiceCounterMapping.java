/**
 * Service to Service Counter Mapping in a Branch
 */
package com.turvo.banking.branch.entities;

import java.util.List;

/**
 * @author anushm
 *
 */
public class ServiceToServiceCounterMapping {
	
	private Long serviceId;
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
