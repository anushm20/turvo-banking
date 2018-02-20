/**
 * 
 */
package com.turvo.banking.bank.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.bank.entities.Bank;
import com.turvo.banking.bank.repositories.BankRepository;

/**
 * @author anushm
 *
 */
@Service
public class BankCrudServiceImpl implements BankCrudService {
	
	@Autowired
	BankRepository bankRepo;

	/* (non-Javadoc)
	 * @see com.turvo.banking.bank.services.BankCrudService#getBankById(java.lang.Long)
	 */
	@Override
	public Bank getBankById(Integer bankId) {
		return bankRepo.findOne(bankId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.bank.services.BankCrudService#createBankById(com.turvo.banking.bank.entities.Bank)
	 */
	@Override
	public int createBankById(Bank bank) {
		bankRepo.save(bank);
		return bank.getBankId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.bank.services.BankCrudService#updateBank(com.turvo.banking.bank.entities.Bank)
	 */
	@Override
	public boolean updateBank(Bank bank) {
		Bank saved = bankRepo.save(bank);
		if(Objects.nonNull(saved))
			return true;
		else 
			return false;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.bank.services.BankCrudService#deleteBank(java.lang.Long)
	 */
	@Override
	public boolean deleteBank(Integer bankId) {
		bankRepo.delete(bankId);
		Bank bank = getBankById(bankId);
		if(Objects.isNull(bank))
			return true;
		else
			return false;
	}

}
