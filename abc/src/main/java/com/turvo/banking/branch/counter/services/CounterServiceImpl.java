/**
 * Implementation of Services for Service Counter
 */
package com.turvo.banking.branch.counter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.repositories.CounterRepository;

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
	public List<Long> getCountersByType(CounterType type) {
		List<Long> counters = new ArrayList<>();
		counterRepo.findAll().forEach((Counter counter)->{
			if(type.toString().equals
						(counter.getCounterType().toString())) {
				counters.add(counter.getCounterId());
			}
		});
		return counters;
	}
	
	/*
	 * @see com.turvo.banking.branch.counter.services.CounterService#
	 * getCounterById(java.lang.Long)
	 * (non-Javadoc)
	 * @see com.turv
	 */
	@Override
	public Counter getCounterById(Long counterId) {
		return counterRepo.findOne(counterId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.CounterService#
	 * createCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public Long createCounter(Counter counter) {
		counterRepo.save(counter);
		return counter.getCounterId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService
	 * #updateCounter(com.turvo.banking.branch.counter.entities.Counter)
	 */
	@Override
	public boolean updateCounter(Counter counter) {
		Counter savedCounter = counterRepo.save(counter);
		if(Objects.nonNull(savedCounter)) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService
	 * #deleteCounter(java.lang.Long)
	 */
	@Override
	public boolean deleteCounter(Long counterId) {
		counterRepo.delete(counterId);
		Counter counter = getCounterById(counterId);
		if(Objects.isNull(counter)) {	
			return true;
		} else {
			return false;
		}
	}

}
