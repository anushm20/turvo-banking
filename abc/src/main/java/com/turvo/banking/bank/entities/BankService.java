/**
 * Bank Service Entity
 */
package com.turvo.banking.bank.entities;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@RedisHash("bankservices")
public class BankService {
	
	@Id
	@ApiModelProperty(notes = "The database generated Service ID")
	private Long serviceId;
	
	@NotNull
	@ApiModelProperty(notes = "Name of the service in Bank say Deposit etc",required=true)
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
