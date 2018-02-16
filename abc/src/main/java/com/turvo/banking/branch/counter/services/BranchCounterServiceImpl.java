/**
 * Implementation of Services for Service Counter
 */
package com.turvo.banking.branch.counter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.counter.repositories.BranchCounterRepository;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.common.services.SequencesServices;

/**
 * @author anushm
 *
 */
@Service
public class BranchCounterServiceImpl implements BranchCounterService {
	
	@Autowired
	BranchCounterRepository counterRepo;
	
	@Autowired
	SequencesServices sequenceService;

	@Override
	public List<Long> getPremiumServiceCounters() {
		List<Long> counters = new ArrayList<>();
		counterRepo.findAll().forEach((BranchCounter counter)->{
			if(CounterType.PREMIUM.toString().equals
						(counter.getCounterType().toString())) {
				counters.add(counter.getServiceCounterId());
			}
		});
		return counters;
	}
	
	@Override
	public BranchCounter getServiceCounterById(Long counterId) {
		return counterRepo.findOne(counterId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#getQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public PriorityQueue<CustomerToken> getQueueForServiceCounter(Long counterId) {
		BranchCounter counter = getServiceCounterById(counterId);
		if(Objects.nonNull(counter))
			return counter.getTokenQueue();
		else 
			return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#createQueueForServiceCounter(com.turvo.banking.branch.counter.entities.ServiceCounter)
	 */
	@Override
	public Long createServiceCounter(BranchCounter counter) {
		counter.setServiceCounterId(sequenceService.getSequenceForEntity("servicecounters"));
		counterRepo.save(counter);
		return counter.getServiceCounterId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#updateQueueForServiceCounter(com.turvo.banking.branch.counter.entities.ServiceCounter)
	 */
	@Override
	public void updateServiceCounter(BranchCounter counter) {
		counterRepo.save(counter);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.services.ServiceCounterService#deleteQueueForServiceCounter(java.lang.Long)
	 */
	@Override
	public void deleteServiceCounter(Long counterId) {
		counterRepo.delete(counterId);
	}

}