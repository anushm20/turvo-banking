/**
 * DAO implementation for Counter
 */
package com.turvo.banking.branch.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.CounterType;
import com.turvo.banking.branch.model.Token;

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
		if(Objects.nonNull(counterIds) && counterIds.size() > 0) {
			Query query = em.createNamedQuery("Counter.findMinTokensCounter");
			query.setParameter("counterIdList", counterIds);
			List<Long> dbCounters = query.getResultList();
			System.out.println(dbCounters);
			if(Objects.nonNull(dbCounters) && dbCounters.size() > 0) {
				return getCounterById(loadBalanceCounters(counterIds, dbCounters));
			} else {
				// First time counters are assigned for the day
				return getCounterById(counterIds.get(0));
			}
		} else {
			// Throw exception
			return null;
		}
	}
	private Long loadBalanceCounters(List<Long> counterIds, List<Long> dbCounters) {
		Long counterId = null;
		if(counterIds.size() == dbCounters.size()) {
			// All counters are busy with atleast one token
			counterId = dbCounters.get(0);
		} else {
			// Some counters are free . They don't have tokens assigned yet
			Optional<Long> optVal = counterIds.stream().filter(id->!dbCounters.
										contains(id)).findFirst();
			counterId = optVal.get();
		}
		return counterId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Token getTokenForCounter(Long counterId) {
		Query query = em.createNamedQuery("Counter.getNextToken",Token.class);
		query.setParameter("counterId", counterId);
		List<Token> tokens = query.getResultList();
		if(Objects.nonNull(tokens) && tokens.size() > 0)
			return tokens.get(0);
		else 
			return null;
	}
	
	@Override
	public List<Integer> getTokenNumbersAtCounter(Long counterId) {
		Query query= em.createNamedQuery("Counter.findTokenNumbersByCounter");
		query.setParameter("counterId", counterId);
		@SuppressWarnings("unchecked")
		List<Integer> numbers = query.getResultList();
		return numbers;
	}

}
