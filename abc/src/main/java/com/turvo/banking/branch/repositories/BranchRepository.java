/**
 * 
 */
package com.turvo.banking.branch.repositories;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.entities.Branch;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Repository
public interface BranchRepository extends BankingRepository<Branch, Integer> {

}