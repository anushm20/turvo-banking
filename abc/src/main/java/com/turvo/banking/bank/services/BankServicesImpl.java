/**
 * Implementation for services of Bank Service 
 */
package com.turvo.banking.bank.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.bank.entities.BankService;
import com.turvo.banking.bank.repositories.BankServiceRepository;
import com.turvo.banking.common.services.SequencesServices;

/**
 * @author anushm
 *
 */
@Service
public class BankServicesImpl implements BankServices {
	
	@Autowired
	private BankServiceRepository serviceRepository;
	
	@Autowired
	SequencesServices sequenceService;

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
		service.setServiceId(sequenceService.getSequenceForEntity("bankservices"));
		serviceRepository.save(service);
		return service.getServiceId();
		
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#updateBankService(com.turvo.banking.entities.BankService)
	 */
	@Override
	public void updateBankService(BankService service) {
		serviceRepository.save(service);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#deleteBankService(java.lang.Long)
	 */
	@Override
	public void deleteBankService(Long serviceId) {
		serviceRepository.delete(serviceId);
	}

}
