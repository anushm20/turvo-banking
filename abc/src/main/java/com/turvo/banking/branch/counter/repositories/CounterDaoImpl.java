/**
 * DAO implementation for Counter
 */
package com.turvo.banking.branch.counter.repositories;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;

/**
 * @author anushm
 *
 */
@Repository
public class CounterDaoImpl implements CounterDao {
	
	@PersistenceContext
	EntityManager em;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterDao#findByBrServiceIdAndCounterType(java.lang.Long, com.turvo.banking.branch.counter.entities.CounterType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Counter> findByBrServiceIdAndCounterType(Long serviceId, CounterType type) {
		Query query = em.createNamedQuery("Counter.findByServiceAndType",Counter.class);
		query.setParameter("brServiceId", serviceId);
		query.setParameter("type", type);
		List<Counter> counters = query.getResultList();
		return counters;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterDao#getCounterById(java.lang.Long)
	 */
	@Override
	public Counter getCounterById(Long counterId) {
		return em.find(Counter.class, counterId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterDao#createCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public Long createCounter(Counter counter) {
		em.persist(counter);
		em.flush();
		return counter.getCounterId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterDao#updateCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public boolean updateCounter(Counter counter) {
		Counter saved = em.merge(counter);
		if(Objects.nonNull(saved))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.repositories.CounterDao#deleteCounter(java.lang.Long)
	 */
	@Override
	public boolean deleteCounter(Long counterId) {
		Counter counter = getCounterById(counterId);
		if(Objects.nonNull(counter)) {
			em.remove(counter);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@org.springframework.transaction.annotation.Transactional
	public Counter getCounterWithMinTokens(List<Long> counterIds) {
		Query query = em.createNamedQuery("Counter.findMinTokensCounter", TokenCounterMapper.class);
		query.setParameter("counterIdList", counterIds);
		List<TokenCounterMapper> counters = query.getResultList();
		if(Objects.nonNull(counters) && counters.size() > 0) {
			return getCounterById(counters.get(0).getCounter().getCounterId());
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getTokensInCounter(Long counterId) {
		Query query = em.createNamedQuery("TokenCounterMapper.getNextToken");
		query.setParameter("counterId", counterId);
		List<Long> tokens = query.getResultList();
		if(Objects.nonNull(tokens) && tokens.size() > 0)
			return tokens;
		else 
			return null;
	}

}
