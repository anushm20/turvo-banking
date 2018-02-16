/**
 * 
 */
package com.turvo.banking.branch.counter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.counter.entities.ServiceCounter;

/**
 * @author anushm
 *
 */
@Repository
public interface ServiceCounterRepository extends CrudRepository<ServiceCounter, Long> {

}
