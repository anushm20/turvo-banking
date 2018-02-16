/**
 * 
 */
package com.turvo.banking.branch.counter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.counter.entities.BranchCounter;

/**
 * @author anushm
 *
 */
@Repository
public interface BranchCounterRepository extends CrudRepository<BranchCounter, Long> {

}
