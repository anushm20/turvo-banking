/**
 * Implementation for service interface for branch operations
 */
package com.turvo.banking.branch.counter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.counter.entities.BranchCounterMapping;
import com.turvo.banking.branch.counter.repositories.BranchCounterMappingRepository;

/**
 * @author anushm
 *
 */
@Service
public class BranchCounterMappingServicesImpl implements BranchCounterMappingServices {
	
	@Autowired
	BranchCounterMappingRepository branchRepo;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#getServiceCountersForService(java.lang.Long)
	 */
	@Override
	public List<Long> getServiceCountersForService(Long serviceId) {
		return branchRepo.findOne(serviceId).getServiceCounters();
	}
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#createServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void createServiceToServiceCounterMapping(BranchCounterMapping mapping) {
		branchRepo.save(mapping);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#updateServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void updateServiceToServiceCounterMapping(BranchCounterMapping mapping) {
		branchRepo.save(mapping);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#deleteServiceToServiceCounterMapping(java.lang.Long)
	 */
	@Override
	public void deleteServiceToServiceCounterMapping(Long serviceId) {
		branchRepo.delete(serviceId);
	}

}
