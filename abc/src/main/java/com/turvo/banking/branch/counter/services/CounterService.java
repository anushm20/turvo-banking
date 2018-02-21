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
	List<Counter> getCountersByType(CounterType type);
	
	/**
	 * Get all premium service counters in a branch
	 * @param serviceId, CounterType
	 * @return list  of counter ids
	 */
	List<Counter> getCountersByServiceAndType(Long serviceId,CounterType type);
	
	/**
	 * Method to return the count of tokens in a counter
	 * @param counterId
	 * @return the tokens count in the counter
	 */
	//Integer getTokensCountInACounter(Long counterId);
	
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
