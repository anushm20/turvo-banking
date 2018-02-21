/**
 * Custom repository interface class for counter 
 * Contains dynamic queries required for the operations
 */
package com.turvo.banking.branch.counter.repositories;

import java.util.List;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;

/**
 * @author anushm
 *
 */
public interface CounterRepositoryCustom {
	
	List<Counter> findByCounterType(CounterType type);
	
	List<Counter> findByBrServiceIdAndCounterType(Long serviceId, CounterType type);
	
}
