/**
 * Implementation for services of Bank Service 
 */
package com.turvo.banking.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.bank.dao.BankServiceDao;
import com.turvo.banking.bank.entities.BankService;

/**
 * @author anushm
 *
 */
@Service
public class BankServicesImpl implements BankServices {
	
	@Autowired
	BankServiceDao bankDao;

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#getAllBranchServices()
	 */
	@Override
	public List<BankService> getAllBankServices() {
		// TODO Auto-generated method stub
		return bankDao.getAllBankServices();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#getBankServiceById(java.lang.Long)
	 */
	@Override
	public BankService getBankServiceById(Long serviceId) {
		// TODO Auto-generated method stub
		return bankDao.getBankServiceById(serviceId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#createBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public Long createBankService(BankService service) {
		return bankDao.createBankService(service);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#updateBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public void updateBankService(BankService service) {
		bankDao.updateBankService(service);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#deleteBankService(java.lang.Long)
	 */
	@Override
	public void deleteBankService(Long serviceId) {
		bankDao.deleteBankService(serviceId);
	}

}
