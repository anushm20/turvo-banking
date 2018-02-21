/**
 * Implementation of Services for Service Counter
 */
package com.turvo.banking.branch.counter.services;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.repositories.CounterRepository;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.services.TokenPriorityComparator;

/**
 * @author anushm
 *
 */
@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterRepository counterRepo;

	/**
	 * Method to return counters for a given counter type
	 */
	@Override
	public List<Counter> getCountersByType(CounterType type) {
		return counterRepo.findByCounterType(type);
	}

	/**
	 * Method to return counters for a service based on type
	 */
	@Override
	public List<Counter> getCountersByServiceAndType(Long serviceId, CounterType type) {
		return counterRepo.findByBrServiceIdAndCounterType(serviceId, type);
	}

	/*
	 * @see com.turvo.banking.branch.counter.services.CounterService#
	 * getCounterById(java.lang.Long) (non-Javadoc)
	 */
	@Override
	public Counter getCounterById(Long counterId) {
		Counter counter = counterRepo.findOne(counterId);
		PriorityQueue<Token> queue = new PriorityQueue<>(new TokenPriorityComparator());
		// Populate the queue now
		counter.getQueuedTokens().forEach((queued) -> {
			queue.add(queued.getToken());
		});
		counter.setCounterQueue(queue);
		return counter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.services.CounterService#
	 * createCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public Long createCounter(Counter counter) {
		counterRepo.save(counter);
		return counter.getCounterId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService
	 * #updateCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public boolean updateCounter(Counter counter) {
		Counter savedCounter = counterRepo.save(counter);
		if (Objects.nonNull(savedCounter)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService
	 * #deleteCounter(java.lang.Long)
	 */
	@Override
	public boolean deleteCounter(Long counterId) {
		counterRepo.delete(counterId);
		Counter counter = getCounterById(counterId);
		if (Objects.isNull(counter)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * @Override public Integer getTokensCountInACounter(Long counterId) { return
	 * counterRepo.getCounterTokenSize(counterId); }
	 */

}
