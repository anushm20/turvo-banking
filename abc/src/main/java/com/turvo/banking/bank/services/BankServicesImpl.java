/**
 * Implementation for services of Bank Service 
 */
package com.turvo.banking.bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.bank.model.BankService;
import com.turvo.banking.bank.repositories.BankServiceRepository;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

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
	public BankService getBankServiceById(Long serviceId) 
						throws BankEntityNotFoundException {
		Optional<BankService> service = Optional.ofNullable
							(serviceRepository.findOne(serviceId));
		if(service.isPresent())
			return service.get();
		else
			throw new BankEntityNotFoundException("Bank Service "
					+ "not found with the ID : "+serviceId);
		
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#createBankService
	 * 				(com.turvo.banking.entities.BankService)
	 */
	@Override
	public Long createBankService(BankService service) {
		serviceRepository.save(service);
		return service.getServiceId();

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.services.BankServices#updateBankService
	 * 				(com.turvo.banking.entities.BankService)
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
		BankService service = serviceRepository.findOne(serviceId);
		if(Objects.isNull(service)) {
			return true;
		} else {
			return false;
		}
	}

}
