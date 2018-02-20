/**
 * 
 */
package com.turvo.banking.bank.services;

import com.turvo.banking.bank.entities.Bank;

/**
 * @author anushm
 *
 */
public interface BankCrudService {
	
	Bank getBankById(Integer bankId);
	
	int createBankById(Bank bank);
	
	boolean updateBank(Bank bank);
	
	boolean deleteBank(Integer bankId);
}
