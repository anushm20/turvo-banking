/**
 * Implementation of Services for Service Counter
 */
package com.turvo.banking.branch.counter.services;

import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.counter.dao.ServiceCounterDao;
import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Service
public class ServiceCounterServiceImpl implements ServiceCounterService {
	
	@Autowired
	ServiceCounterDao counterDao;

	@Override
	public List<Long> getPremiumServiceCounters() {
		return counterDao.getPremiumServiceCounters();
	}
	
	@Override
	public ServiceCounter getServiceCounterById(Long counterId) {
		return counterDao.getServiceCounterById(counterId);
	}
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#getQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId) {
		return counterDao.getQueueForServiceCounter(counterId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#createQueueForServiceCounter(com.turvo.banking.branch.counter.entities.ServiceCounter)
	 */
	@Override
	public Long createServiceCounter(ServiceCounter counter) {
		return counterDao.createServiceCounter(counter);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#updateQueueForServiceCounter(com.turvo.banking.branch.counter.entities.ServiceCounter)
	 */
	@Override
	public void updateServiceCounter(ServiceCounter counter) {
		counterDao.updateServiceCounter(counter);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#deleteQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public void deleteServiceCounter(Long counterId) {
		counterDao.deleteServiceCounter(counterId);
	}

}
