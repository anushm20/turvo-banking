/**
 * Used for CRUD operations on Banking services
 */
package com.turvo.banking.bank.services;

import java.util.List;

import com.turvo.banking.bank.model.BankService;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

/**
 * @author anushm
 *
 */
public interface BankServices {
	
	/**
	 * Method to list all available bank services
	 * @return list of bank services
	 */
	List<BankService> getAllBankServices();
	
	/**
	 * Method to return service by service Id
	 * @param serviceId
	 * @return bank service object
	 * @throws BankEntityNotFoundException 
	 */
	BankService getBankServiceById(Long serviceId) 
						throws BankEntityNotFoundException;
	
	/**
	 * Method to create bank service
	 * @param bankService
	 * @return id of bank service
	 */
	Long createBankService(BankService bankService);
	
	/**
	 * Method to update bank service object
	 * @param bankService
	 */
	boolean updateBankService(BankService bankService);
	
	/**
	 * Method to delete bank service object
	 * @param serviceId
	 */
	boolean deleteBankService(Long serviceId);

}
