/**
 * DAO implementation for BankService DAO
 */
package com.turvo.banking.bank.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.turvo.banking.bank.database.BankServicesDB;
import com.turvo.banking.bank.entities.BankService;

/**
 * @author anushm
 *
 */
@Repository
public class BankServiceDaoImpl implements BankServiceDao {

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#getAllBranchServices()
	 */
	@Override
	public List<BankService> getAllBankServices() {
		List<BankService> bankServices = new ArrayList<>();
		for(Long id : BankServicesDB.bankServices.keySet()) {
			bankServices.add(BankServicesDB.bankServices.get(id));
		}
		return bankServices;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#getBankServiceById(java.lang.Long)
	 */
	@Override
	public BankService getBankServiceById(Long serviceId) {
		return BankServicesDB.bankServices.get(serviceId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#createBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public Long createBankService(BankService service) {
		Long id = BankServicesDB.bankServicesCount;
		service.setServiceId(id);
		BankServicesDB.bankServices.put(id, service);
		BankServicesDB.bankServicesCount++;
		return id;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#updateBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public void updateBankService(BankService service) {
		BankServicesDB.bankServices.put(service.getServiceId(),service);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#deleteBankService(java.lang.Long)
	 */
	@Override
	public void deleteBankService(Long serviceId) {
		BankServicesDB.bankServices.remove(serviceId);
	}

}
