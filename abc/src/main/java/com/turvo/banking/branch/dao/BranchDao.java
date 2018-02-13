/**
 * Perform DAO operations in a Branch office related to Service Counter
 * 
 *  Queues & Service Counter Mapping
 */
package com.turvo.banking.branch.dao;

import java.util.List;
import java.util.PriorityQueue;

import com.turvo.banking.branch.entities.ServiceCounter;
import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface BranchDao {
	
	/**
	 * Method to get Service counters for given service id
	 * @param serviceId
	 * @return list of service counters for the service
	 */
	List<Long> getServiceCountersForService(Long serviceId);

	/**
	 * Method to create service to Service counter mapping in a branch
	 * @param mapping
	 */
	void createServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping);

	/**
	 * Method to update service to Service counter mapping in a branch
	 * @param mapping
	 */
	void updateServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping);

	/**
	 * Method to delete service to Service counter mapping in a branch
	 * @param serviceId
	 */
	void deleteServiceToServiceCounterMapping(Long serviceId);
	
	/**
	 * Method to get token queue for that service counter
	 * @param counterId
	 * @return priority queue with customer tokens
	 */
	PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId);
	
	/**
	 * Method to create token queue for that service counter
	 * @param counter
	 */
	void createQueueForServiceCounter(ServiceCounter counter);
	
	/**
	 * Method to update token queue for that service counter
	 * @param counter
	 */
	void updateQueueForServiceCounter(ServiceCounter counter);
	
	/**
	 * Method to delete token queue for that service counter
	 * @param counterId
	 */
	void deleteQueueForServiceCounter(Long counterId);
	
}
