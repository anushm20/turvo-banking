/**
 * DAO interface for counter operations
 */
package com.turvo.banking.branch.dao;

import java.util.List;

import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.CounterType;
import com.turvo.banking.branch.model.Token;

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
	 * get next token id for the given counter based on token priority
	 * @param counterId
	 * @return token id
	 */
	Token getTokenForCounter(Long counterId);
	
	/**
	 * Method to return list of token numbers at a counter
	 * @param counterId
	 * @return list of token numbers
	 */
	List<Integer> getTokenNumbersAtCounter(Long counterId);
	
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
