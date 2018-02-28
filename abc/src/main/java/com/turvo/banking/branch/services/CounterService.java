/**
 * Services for Counter
 */
package com.turvo.banking.branch.services;

import java.util.List;

import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.CounterType;

/**
 * @author anushm
 *
 */
public interface CounterService {
	
	/**
	 * Get all counters for a service based on type
	 * @param serviceId, CounterType
	 * @return list  of counter ids
	 */
	List<Counter> getCountersByServiceAndType(Long serviceId,CounterType type);
	
	/**
	 * Get counter which has minimum tokens assigned 
	 * @param counterIds
	 * @return counter object
	 */
	Counter getCounterWithMinTokens(List<Long> counterIds); 
	
	/**
	 * get next token id for the given counter and update the token status
	 * @param counterId
	 * @return list of token ids
	 */
	List<Long> getTokensInCounter(Long counterId);
	
	/**
	 * Method to return list of token numbers at a counter
	 * @param counterId
	 * @return list of token numbers
	 */
	List<Integer> getTokenNumbersAtCounter(Long counterId);
	
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
	 * Method to update a  counter
	 * @param counter
	 */
	boolean updateCounter(Counter counter);
	
	/**
	 * Method to delete a  counter
	 * @param counterId
	 */
	boolean deleteCounter(Long counterId);
}
