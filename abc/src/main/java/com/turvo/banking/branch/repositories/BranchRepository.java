/**
 * Repository class for Branch operations
 */
package com.turvo.banking.branch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;

/**
 * @author anushm
 *
 */
@Repository
public interface BranchRepository extends CrudRepository<ServiceToServiceCounterMapping, Long> {

}
