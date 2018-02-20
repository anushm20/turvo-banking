/**
 * Repository for Branch services
 */
package com.turvo.banking.branch.repositories;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.entities.BranchService;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Repository
public interface BranchServiceRepository extends BankingRepository<BranchService, Long> {

}
