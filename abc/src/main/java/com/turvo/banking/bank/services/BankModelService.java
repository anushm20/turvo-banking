/**
 * Service Interface for performing CRUD operations 
 * on Bank
 */
package com.turvo.banking.bank.services;

import com.turvo.banking.bank.model.Bank;

/**
 * @author anushm
 *
 */
public interface BankModelService {
	
	/**
	 * Get bank by ID
	 * @param bankId
	 * @return bank object
	 */
	Bank getBankById(Integer bankId);
	
	/**
	 * Create Bank
	 * @param bank
	 * @return Id of the new bank
	 */
	int createBankById(Bank bank);
	
	/**
	 * Update Bank
	 * @param bank
	 * @return success or failure
	 */
	boolean updateBank(Bank bank);
	
	/**
	 * Delete bank
	 * @param bankId
	 * @return success or failure
	 */
	boolean deleteBank(Integer bankId);
}
