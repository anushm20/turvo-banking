/**
 * Custom repository Implementation class for counter 
 * Contains dynamic queries required for the operations
 */
package com.turvo.banking.branch.counter.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;

/**
 * @author anushm
 *
 */
@Repository
@Transactional(readOnly = true)
public class CounterRepositoryCustomImpl implements CounterRepositoryCustom {
	
	@PersistenceContext	
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterRepositoryCustom#getCountersByType(com.turvo.banking.branch.counter.entities.CounterType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Counter> findByCounterType(CounterType type) {
		Query query = entityManager.createNativeQuery("select * from counter where "
				+ "counter_type=?");
		query.setParameter(0, type);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterRepositoryCustom#getCountersByServiceAndType(java.lang.Long, com.turvo.banking.branch.counter.entities.CounterType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Counter> findByBrServiceIdAndCounterType(Long serviceId, CounterType type) {
		Query query = entityManager.createNativeQuery("select * from counter"
				+ " where counter_type=? and branch_service_id=?");
		query.setParameter(0, type);
		query.setParameter(1, serviceId);
		return query.getResultList();
	}

}
