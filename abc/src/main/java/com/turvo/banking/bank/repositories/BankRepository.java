/**
 * 
 */
package com.turvo.banking.bank.repositories;

import org.springframework.stereotype.Repository;

import com.turvo.banking.bank.entities.Bank;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Repository
public interface BankRepository extends BankingRepository<Bank, Integer> {

}
