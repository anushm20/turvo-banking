/**
 * Repository class for bank services
 */
package com.turvo.banking.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.turvo.banking.bank.entities.BankService;

/**
 * @author anushm
 *
 */
@Component
public interface BankServiceRepository extends CrudRepository<BankService, Long> {

}
