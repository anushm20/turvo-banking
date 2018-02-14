/**
 * Service Interface for Branch Operations
 */
package com.turvo.banking.branch.services;

import java.util.List;

import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;

/**
 * @author anushm
 *
 */
public interface BranchServices {
	
	/**
	 * Get list of counters for a service in a branch
	 * @param serviceId
	 * @return list of counter ids
	 */
	List<Long> getServiceCountersForService(Long serviceId);
	
	/**
	 * Method to create a mapping for service and service counter
	 * @param mapping
	 */
	void createServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping);

	/**
	 * Method to update a mapping for service and service counter
	 * @param mapping
	 */
	void updateServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping);

	/**
	 * Method to delete a mapping for service and service counter
	 * @param serviceId
	 */
	void deleteServiceToServiceCounterMapping(Long serviceId);
	
}
