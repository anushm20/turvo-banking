/**
 * Services for Counter
 */
package com.turvo.banking.branch.counter.services;

import java.util.List;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;

/**
 * @author anushm
 *
 */
public interface CounterService {
	
	/**
	 * Get all premium service counters in a branch
	 * @return list  of counter ids
	 */
	List<Long> getCountersByType(CounterType type);
	
	/**
	 * Get Counter by Counter Id
	 * @param counterId
	 * @return Service counter object
	 */
	Counter getCounterById(Long counterId);
	
	/**
	 * Method to Create a counter 
	 * @param counter
	 */
	Long createCounter(Counter counter);
	
	/**
	 * Method to update a service counter
	 * @param counter
	 */
	boolean updateCounter(Counter counter);
	
	/**
	 * Method to delete a service counter
	 * @param counterId
	 */
	boolean deleteCounter(Long counterId);
}
