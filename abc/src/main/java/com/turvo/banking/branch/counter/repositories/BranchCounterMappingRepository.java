/**
 * Repository class for Branch operations
 */
package com.turvo.banking.branch.counter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.counter.entities.BranchCounterMapping;

/**
 * @author anushm
 *
 */
@Repository
public interface BranchCounterMappingRepository extends CrudRepository<BranchCounterMapping, Long> {

}
