/**
 * DAO implementation for Service Counter
 */
package com.turvo.banking.branch.counter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.counter.database.ServiceCounterDatabase;
import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.counter.entities.ServiceCounterType;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Repository
public class ServiceCounterDaoImpl implements ServiceCounterDao {

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.dao.ServiceCounterDao#getPremiumServiceCounters()
	 */
	@Override
	public List<Long> getPremiumServiceCounters() {
		List<Long> counters = new ArrayList<>();
		for (ServiceCounter counter : ServiceCounterDatabase.serviceCounterMap.values()) {
			if(ServiceCounterType.PREMIUM.toString().equals(counter.getCounterType().toString())) {
				counters.add(counter.getServiceCounterId());
			}
		}
		return counters;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.dao.ServiceCounterDao#getServiceCounterById(java.lang.Long)
	 */
	@Override
	public ServiceCounter getServiceCounterById(Long counterId) {
		return ServiceCounterDatabase.serviceCounterMap.get(counterId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.dao.ServiceCounterDao#getQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId) {
		return ServiceCounterDatabase.serviceCounterMap.get(counterId).getTokenQueue();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.dao.ServiceCounterDao#createServiceCounter(com.turvo.banking.branch.counter.entities.ServiceCounter)
	 */
	@Override
	public Long createServiceCounter(ServiceCounter counter) {
		Long id = ServiceCounterDatabase.serviceCounterId;
		counter.setServiceCounterId(id);
		ServiceCounterDatabase.serviceCounterId++;
		ServiceCounterDatabase.serviceCounterMap.put(id,counter);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.dao.ServiceCounterDao#updateServiceCounter(com.turvo.banking.branch.counter.entities.ServiceCounter)
	 */
	@Override
	public void updateServiceCounter(ServiceCounter counter) {
		ServiceCounterDatabase.serviceCounterMap.put(counter.getServiceCounterId(),counter);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.dao.ServiceCounterDao#deleteServiceCounter(java.lang.Long)
	 */
	@Override
	public void deleteServiceCounter(Long counterId) {
		ServiceCounterDatabase.serviceCounterMap.remove(counterId);
	}

}
