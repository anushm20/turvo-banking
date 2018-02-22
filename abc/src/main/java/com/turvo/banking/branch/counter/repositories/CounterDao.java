/**
 * DAO interface for counter operations
 */
package com.turvo.banking.branch.counter.repositories;

import java.util.List;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;

/**
 * @author anushm
 *
 */
public interface CounterDao {

	/**
	 * Fetch list of counters for given service and counter type
	 * @param serviceId
	 * @param type
	 * @return list of counters
	 */
	List<Counter> findByBrServiceIdAndCounterType
						(Long serviceId, CounterType type);
	
	/**
	 * Get counter which has minimum tokens assigned 
	 * @param counterIds
	 * @return counter object
	 */
	Counter getCounterWithMinTokens(List<Long> counterIds); 
	
	
	/**
	 * get next token id for the given counter and update the token status
	 * @param counterId
	 * @return token id
	 */
	List<Long> getTokensInCounter(Long counterId);
	
	/**
	 * Get Service Counter by Counter Id
	 * @param counterId
	 * @return Service counter object
	 */
	Counter getCounterById(Long counterId);
	
	/**
	 * Method to Create a service counter 
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
