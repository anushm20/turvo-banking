/**
 * 
 */
package com.turvo.banking.branch.counter.operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.entities.BranchService;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
@Component
public class CountersUtil {
	
	@Autowired
	BranchServices branchServices;
	
	@Autowired
	CounterService counterServices;
	
	public Set<TokenCounterMapper> placeTokenInFirstCounter(Long serviceId,Token token) throws InvalidDataException {
		Set<TokenCounterMapper> tokenCounters = new HashSet<>();
		BranchService brService = branchServices.getBranchServiceById(serviceId);
		// Get the counters for the services based on token type
		List<Counter> counters = getListOfCountersForService(token, serviceId);
		if (Objects.nonNull(counters) && counters.size() > 0) {
			if (brService.getMultiCounter()) {
				// Assign counter for Multi counter service
				// Generate Map of counter order to counter object
				// For each order pass it to the method to get counter
				tokenCounters.add(assignCounterForMultiCounterService
							(token, counters));
			} else {
				// Assign counter for single counter service
				Counter counter = getCounterFromList(counters);
				tokenCounters.add(new TokenCounterMapper(token, counter));
			}
		} else {
			throw new EntityNotFoundException("Counters cannot be found for the service "+
						serviceId+" for customer type :"+token.getCustomer().getType());
		}
		return tokenCounters;
	}
	
	/**
	 * Method to return list of counters for a given service based 
	 * on customer type
	 * @param token
	 * @param serviceId
	 * @return list of counter objects
	 * @throws InvalidDataException 
	 */
	public List<Counter> getListOfCountersForService(Token token, Long serviceId) 
					throws InvalidDataException {
		List<Counter> counters = new ArrayList<>();
		if (CounterType.PREMIUM.toString().equals(token.getCustomer().
									getType().toString())) {
			// Assumption is if branch chooses SEPARATE strategy there
			// should be premium counters for each service
			counters.addAll(counterServices.getCountersByServiceAndType
					(serviceId, CounterType.PREMIUM));
		} else if (CounterType.REGULAR.toString().equals(token.getCustomer().
									getType().toString())) {
			counters.addAll(counterServices.getCountersByServiceAndType
					(serviceId, CounterType.REGULAR));
		} else {
			throw new InvalidDataException("Invalid Customer Type.");
		}
		return counters;
	}
	
	/**
	 * Assign counter for multicounter service
	 * @param token
	 * @param tokenCounters
	 * @param counterOrder
	 * @param counters
	 * @return final counter order number
	 */
	private TokenCounterMapper assignCounterForMultiCounterService(Token token, 
			List<Counter> counters) {
		Map<Integer, List<Counter>> counterOrderMap = 
				generateMultiCounterOrderMap(counters);
		for (Integer order : counterOrderMap.keySet()) {
			Counter counter = getCounterFromList(counterOrderMap.get(order));
			TokenCounterMapper mapper = new TokenCounterMapper
					(token, counter);
			return mapper;
		}
		return null;
	}
	
	
	/**
	 * In case of a multi counter service, if each service has multiple counters
	 * generate a map for them using the counter order customer has to visit
	 * 
	 * @param counters
	 * @return map of counter order and list of counters
	 */
	public Map<Integer, List<Counter>> generateMultiCounterOrderMap(List<Counter> counters) {
		Map<Integer, List<Counter>> counterOrderMap = new TreeMap<>();
		for (Counter counter : counters) {
			if (Objects.nonNull(counterOrderMap.get(counter.getOrder()))) {
				counterOrderMap.get(counter.getOrder()).add(counter);
			} else {
				List<Counter> orderCtrs = new ArrayList<>();
				orderCtrs.add(counter);
				counterOrderMap.put(counter.getOrder(), orderCtrs);
			}
		}
		return counterOrderMap;

	}
	
	/**
	 * Get the final counter customer has to go based on the list of counters
	 * available for the service
	 * 
	 * @param counters
	 * @return final counter which customer has to go
	 */
	public Counter getCounterFromList(List<Counter> counters) {
		// if size is 1 return the counter
		if (counters.size() == 1) {
			return counters.get(0);
		} else {
			// more than 1 load balance them
			// Fetch from DB which has less number of tokens
			List<Long> counterIds = new ArrayList<>();
			counters.forEach((counter)->counterIds.add(counter.getCounterId()));
			return counterServices.getCounterWithMinTokens(counterIds);
			//return loadBalanceCounters(counters);
		}
	}

}
