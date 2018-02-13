/**
 * Used for CRUD operations on Banking services
 */
package com.turvo.banking.services;

import java.util.List;

import com.turvo.banking.entities.BankService;

/**
 * @author anushm
 *
 */
public interface BankServices {
	
	/**
	 * Method to list all available bank services
	 * @return
	 */
	List<BankService> getAllBankServices();
	
	BankService getBankServiceById(Long serviceId);
	
	Long createBankService(BankService service);
	
	void updateBankService(BankService service);
	
	void deleteBankService(Long serviceId);

}
