/**
 * 
 */
package com.turvo.banking.bank.repositories;

import org.springframework.stereotype.Repository;

import com.turvo.banking.bank.model.Bank;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Repository
public interface BankRepository extends BankingRepository<Bank, Integer> {

}
