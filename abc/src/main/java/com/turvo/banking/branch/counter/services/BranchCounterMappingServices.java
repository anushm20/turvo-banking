/**
 * Service Interface for Branch Operations
 */
package com.turvo.banking.branch.counter.services;

import java.util.List;

import com.turvo.banking.branch.counter.entities.BranchCounterMapping;

/**
 * @author anushm
 *
 */
public interface BranchCounterMappingServices {
	
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
	void createServiceToServiceCounterMapping(BranchCounterMapping mapping);

	/**
	 * Method to update a mapping for service and service counter
	 * @param mapping
	 */
	void updateServiceToServiceCounterMapping(BranchCounterMapping mapping);

	/**
	 * Method to delete a mapping for service and service counter
	 * @param serviceId
	 */
	void deleteServiceToServiceCounterMapping(Long serviceId);
	
}
