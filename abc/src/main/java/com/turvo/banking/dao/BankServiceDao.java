/**
 * 
 */
package com.turvo.banking.dao;

import java.util.List;

import com.turvo.banking.entities.BankService;

/**
 * @author anushm
 *
 */
public interface BankServiceDao {
	
	List<BankService> getAllBankServices();
	
	BankService getBankServiceById(Long serviceId);
	
	Long createBankService(BankService service);
	
	void updateBankService(BankService service);
	
	void deleteBankService(Long serviceId);

}
