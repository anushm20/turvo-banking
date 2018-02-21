/**
 * Repository class for Counter operations
 */
package com.turvo.banking.branch.counter.repositories;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Repository
public interface CounterRepository extends 
		BankingRepository<Counter,Long>,CounterRepositoryCustom {

}
