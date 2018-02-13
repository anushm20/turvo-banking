/**
 * 
 */
package com.turvo.banking.services;

import java.util.List;

import com.turvo.banking.entities.BankService;

/**
 * @author anushm
 *
 */
public interface BankServices {
	
	List<BankService> getAllBranchServices();
	
	BankService getBankServiceById(Long serviceId);
	
	Long createBankService(BankService service);
	
	void updateBankService(BankService service);
	
	void deleteBankService(Long serviceId);

}
