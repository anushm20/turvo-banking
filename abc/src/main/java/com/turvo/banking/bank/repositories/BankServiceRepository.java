/**
 * Repository class for bank services
 */
package com.turvo.banking.bank.repositories;

import org.springframework.stereotype.Component;

import com.turvo.banking.bank.model.BankService;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Component
public interface BankServiceRepository extends BankingRepository<BankService, Long> {

}
