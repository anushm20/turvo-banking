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

	void createServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping);

	void updateServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping);

	void deleteServiceToServiceCounterMapping(Long serviceId);
	
	PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId);
	
	void createQueueForServiceCounter(ServiceCounter counter);
	
	void updateQueueForServiceCounter(ServiceCounter counter);
	
	void deleteQueueForServiceCounter(Long counterId);
	
}
