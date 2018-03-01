/**
 * Implementation of Services for Counter
 */
package com.turvo.banking.branch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.dao.CounterDao;
import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.CounterType;
import com.turvo.banking.branch.model.Token;

/**
 * @author anushm
 *
 */
@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterDao counterDao;

	/**
	 * Method to return counters for a service based on type
	 */
	@Override
	public List<Counter> getCountersByServiceAndType(Long serviceId, CounterType type) {
		return counterDao.findByBrServiceIdAndCounterType(serviceId, type);
	}

	/*
	 * @see com.turvo.banking.branch.counter.services.CounterService#
	 * getCounterById(java.lang.Long) (non-Javadoc)
	 */
	@Override
	public Counter getCounterById(Long counterId) {
		return counterDao.getCounterById(counterId);
	}
	
	/**
	 * Method to get counter with minimum tokens 
	 */
	@Override
	public Counter getCounterWithMinTokens(List<Long> counterIds) {
		return counterDao.getCounterWithMinTokens(counterIds);
	}

	/**
	 * Method to get tokens in a counter. Only Ids
	 */
	@Override
	public Token getNextTokensForCounter(Long counterId) {
		return counterDao.getTokenForCounter(counterId);
	}
	
	/**
	 * Method to get tokens in  a counter. Only Numbers
	 */
	@Override
	public List<Integer> getTokenNumbersAtCounter(Long counterId) {
		return counterDao.getTokenNumbersAtCounter(counterId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.services.CounterService#
	 * createCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public Long createCounter(Counter counter) {
		return counterDao.createCounter(counter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService
	 * #updateCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public boolean updateCounter(Counter counter) {
		return counterDao.updateCounter(counter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService
	 * #deleteCounter(java.lang.Long)
	 */
	@Override
	public boolean deleteCounter(Long counterId) {
		return counterDao.deleteCounter(counterId);
	}

}
