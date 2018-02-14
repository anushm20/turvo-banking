/**
 * Services for Service Counter
 */
package com.turvo.banking.branch.counter.services;

import java.util.List;
import java.util.PriorityQueue;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface ServiceCounterService {
	
	/**
	 * Get all premimum service counters in a branch
	 * @return list  of counter ids
	 */
	List<Long> getPremiumServiceCounters();
	
	/**
	 * Get Service Counter by Counter Id
	 * @param counterId
	 * @return Service counter object
	 */
	ServiceCounter getServiceCounterById(Long counterId);
	
	/**
	 * Get Queue for a particular service counter
	 * @param counterId
	 * @return Priority queue
	 */
	PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId);
	
	/**
	 * Method to Create a service counter 
	 * @param counter
	 */
	Long createServiceCounter(ServiceCounter counter);
	
	/**
	 * Method to update a service counter
	 * @param counter
	 */
	void updateServiceCounter(ServiceCounter counter);
	
	/**
	 * Method to delete a service counter
	 * @param counterId
	 */
	void deleteServiceCounter(Long counterId);
}
