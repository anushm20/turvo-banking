/**
 * 
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
	
	//Integer getCounterTokenSize(Long counterId);

}
