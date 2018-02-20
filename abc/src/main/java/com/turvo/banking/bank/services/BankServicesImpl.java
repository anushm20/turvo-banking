/**
 * Implementation for services of Bank Service 
 */
package com.turvo.banking.bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.bank.entities.BankService;
import com.turvo.banking.bank.repositories.BankServiceRepository;

/**
 * @author anushm
 *
 */
@Service
public class BankServicesImpl implements BankServices {

	@Autowired
	private BankServiceRepository serviceRepository;

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#getAllBranchServices()
	 */
	@Override
	public List<BankService> getAllBankServices() {
		List<BankService> services = new ArrayList<BankService>();
		serviceRepository.findAll().forEach(services::add);
		return services;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#getBankServiceById(java.lang.Long)
	 */
	@Override
	public BankService getBankServiceById(Long serviceId) {
		return serviceRepository.findOne(serviceId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#createBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public Long createBankService(BankService service) {
		serviceRepository.save(service);
		return service.getServiceId();

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#updateBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public boolean updateBankService(BankService service) {
		BankService saved = serviceRepository.save(service);
		if(Objects.nonNull(saved)) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#deleteBankService(java.lang.Long)
	 */
	@Override
	public boolean deleteBankService(Long serviceId) {
		serviceRepository.delete(serviceId);
		BankService service = getBankServiceById(serviceId);
		if(Objects.isNull(service)) {
			return true;
		} else {
			return false;
		}
	}

}
