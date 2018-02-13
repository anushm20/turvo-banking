/**
 * 
 */
package com.turvo.banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.dao.BankServiceDao;
import com.turvo.banking.entities.BankService;

/**
 * @author anushm
 *
 */
@Service("bankServices")
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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#createBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public Long createBankService(BankService service) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#updateBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public void updateBankService(BankService service) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#deleteBankService(java.lang.Long)
	 */
	@Override
	public void deleteBankService(Long serviceId) {
		// TODO Auto-generated method stub

	}

}
