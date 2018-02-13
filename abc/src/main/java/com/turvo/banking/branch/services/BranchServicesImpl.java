/**
 * 
 */
package com.turvo.banking.branch.services;

import java.util.List;
import java.util.PriorityQueue;

import com.turvo.banking.branch.entities.ServiceCounter;
import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class BranchServicesImpl implements BranchServices {

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#getServiceCountersForService(java.lang.Long)
	 */
	@Override
	public List<Long> getServiceCountersForService(Long serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#createServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void createServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#updateServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void updateServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#deleteServiceToServiceCounterMapping(java.lang.Long)
	 */
	@Override
	public void deleteServiceToServiceCounterMapping(Long serviceId) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#getQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#createQueueForServiceCounter(com.turvo.banking.branch.entities.ServiceCounter)
	 */
	@Override
	public void createQueueForServiceCounter(ServiceCounter counter) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#updateQueueForServiceCounter(com.turvo.banking.branch.entities.ServiceCounter)
	 */
	@Override
	public void updateQueueForServiceCounter(ServiceCounter counter) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#deleteQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public void deleteQueueForServiceCounter(Long counterId) {
		// TODO Auto-generated method stub

	}

}
