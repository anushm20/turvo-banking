/**
 * DAO implementation for BankService DAO
 */
package com.turvo.banking.bank.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.turvo.banking.bank.model.BankService;

/**
 * @author anushm
 *
 */
@Repository
public class BankServiceDaoImpl implements BankServiceDao {

	@PersistenceContext	
	private EntityManager entityManager;	

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#getAllBranchServices()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankService> getAllBankServices() {
		
		Query query = entityManager.createNamedQuery("BankService.findAll",BankService.class);
		List<BankService> bankServices = query.getResultList();
		return bankServices;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#getBankServiceById(java.lang.Long)
	 */
	@Override
	public BankService getBankServiceById(Long serviceId) {
		return entityManager.find(BankService.class, serviceId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#createBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	@Transactional
	public Long createBankService(BankService bankService) {
		entityManager.persist(bankService);
		entityManager.flush();
		return bankService.getServiceId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#updateBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	@Transactional
	public void updateBankService(BankService bankService) {
		entityManager.merge(bankService);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.dao.BankServiceDao#deleteBankService(java.lang.Long)
	 */
	@Override
	@Transactional
	public boolean deleteBankService(Long serviceId) {
		BankService bankService = getBankServiceById(serviceId);
		if(Objects.nonNull(bankService)) {
			entityManager.remove(bankService);
			return true;
		} else { 
			return false;
		}
	}

}