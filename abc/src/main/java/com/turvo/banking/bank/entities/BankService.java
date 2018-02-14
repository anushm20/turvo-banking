/**
 * Bank Service Entity
 */
package com.turvo.banking.bank.entities;

/**
 * @author anushm
 *
 */

public class BankService {
	
	private Long serviceId;
	private String serviceName;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
