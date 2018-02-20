/**
 * Bank Service Entity
 */
package com.turvo.banking.bank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="bank_service")
@NamedQueries({
	@NamedQuery(name="BankService.findAll", query="from BankService")
})
public class BankService {
	
	@Id
	@GeneratedValue(generator = "BankServiceId")
	@GenericGenerator(
			name="BankServiceId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="BankServiceImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.bank.entities.BankService")
			}
			)
	@Column(name="service_id")
	@ApiModelProperty(notes = "The database generated Service ID")
	private Long serviceId;
	
	@NotNull
	@Column(name="service_name")
	@ApiModelProperty(notes = "Name of the service in"
			+ " Bank say Deposit etc",required=true)
	private String serviceName;
	
	@ManyToOne
	@JoinColumn(name="bank_id")
	@ApiModelProperty(notes="tells the service is associated with which bank")
	private Bank bank;

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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
}
