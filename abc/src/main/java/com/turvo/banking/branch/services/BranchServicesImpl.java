/**
 * Implementation for service interface for branch operations
 */
package com.turvo.banking.branch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.dao.BranchDao;
import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;

/**
 * @author anushm
 *
 */
@Service
public class BranchServicesImpl implements BranchServices {
	
	@Autowired
	BranchDao branchDao;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#getServiceCountersForService(java.lang.Long)
	 */
	@Override
	public List<Long> getServiceCountersForService(Long serviceId) {
		return branchDao.getServiceCountersForService(serviceId);
	}
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#createServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void createServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping) {
		branchDao.createServiceToServiceCounterMapping(mapping);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#updateServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void updateServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping) {
		branchDao.updateServiceToServiceCounterMapping(mapping);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#deleteServiceToServiceCounterMapping(java.lang.Long)
	 */
	@Override
	public void deleteServiceToServiceCounterMapping(Long serviceId) {
		branchDao.deleteServiceToServiceCounterMapping(serviceId);
	}

}
