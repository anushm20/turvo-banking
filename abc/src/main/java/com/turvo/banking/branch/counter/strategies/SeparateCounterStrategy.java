/**
 * Implementation class for separate counter strategies
 */
package com.turvo.banking.branch.counter.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.comparators.CounterTokenSizeComparator;
import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.entities.BranchService;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.branch.token.services.TokenService;

/**
 * @author anushm
 *
 */
@Component("separateCounterStrategy")
public class SeparateCounterStrategy implements CounterStrategyPicker {

	@Autowired
	CounterService counterServices;

	@Autowired
	BranchServices branchServices;

	@Autowired
	TokenService tokenServices;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turvo.banking.branch.counter.operations.CounterStrategyPicker#
	 * updateServiceCounterQueue(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public boolean updateCounterQueue(Token token) {
		Set<TokenCounterMapper> tokenCounters = new HashSet<>();
		// Since we are following separate counter strategy
		// Let's keep priority same as token number
		token.setPriority(token.getNumber());
		int counterOrder = 0;
		// Get the services list
		for (Long serviceId : token.getBranchServices()) {
			// Get the Branch Service Object
			BranchService brService = branchServices.getBranchServiceById(serviceId);
			// Get the counters for the services based on token type
			List<Counter> counters = getListOfCountersForService(token, serviceId);
			if (Objects.nonNull(counters) && counters.size() > 0) {
				if (brService.getMultiCounter()) {
					// Assign counter for Multi counter service
					// Generate Map of counter order to counter object
					// For each order pass it to the method to get counter
					counterOrder = assignCounterForMultiCounterService
								(token, tokenCounters, counterOrder, counters);
				} else {
					// Assign counter for single counter service
					Counter counter = getCounterFromList(counters);
					tokenCounters.add(new TokenCounterMapper(token, counter, counterOrder));
					counterOrder++;
				}
			} else {
				// TODO throw exception
				// Counters not found for the services selected
			}
		}
		// Assign the token
		token.setCounters(tokenCounters);
		token.setStatus(TokenStatus.QUEUED);
		// All counters are assigned in the order
		// save them now
		boolean success = tokenServices.updateToken(token);
		return success;
	}
	
	/**
	 * Assign counter for multicounter service
	 * @param token
	 * @param tokenCounters
	 * @param counterOrder
	 * @param counters
	 * @return final counter order number
	 */
	private int assignCounterForMultiCounterService(Token token, 
						Set<TokenCounterMapper> tokenCounters,
						int counterOrder, List<Counter> counters) {
		Map<Integer, List<Counter>> counterOrderMap = 
						generateMultiCounterOrderMap(counters);
		for (Integer order : counterOrderMap.keySet()) {
			Counter counter = getCounterFromList(counterOrderMap.get(order));
			tokenCounters.add(new TokenCounterMapper
							(token, counter, counterOrder));
			counterOrder++;
		}
		return counterOrder;
	}
	
	/**
	 * Method to return list of counters for a given service based 
	 * on customer type
	 * @param token
	 * @param serviceId
	 * @return list of counter objects
	 */
	private List<Counter> getListOfCountersForService(Token token, Long serviceId) {
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
			// TODO Throw exception
			// Unknown customer type exception
		}
		return counters;
	}

	/**
	 * Get the final counter customer has to go based on the list of counters
	 * available for the service
	 * 
	 * @param counters
	 * @return final counter which customer has to go
	 */
	private Counter getCounterFromList(List<Counter> counters) {
		// if size is 1 return the counter
		if (counters.size() == 1) {
			return counters.get(0);
		} else {
			// more than 1 load balance them
			return loadBalanceCounters(counters);
		}
	}

	/**
	 * Method to do load balancing counters for a given service type
	 * 
	 * @param counters
	 * @return counter which has lowest number of tokens
	 */
	private Counter loadBalanceCounters(List<Counter> counters) {
		// Try to load balance the tokens
		// Initialize the token list if it is null
		Collections.sort(counters, new CounterTokenSizeComparator());
		return counters.get(0);
	}

	/**
	 * In case of a multi counter service, if each service has multiple counters
	 * generate a map for them using the counter order customer has to visit
	 * 
	 * @param counters
	 * @return map of counter order and list of counters
	 */
	private Map<Integer, List<Counter>> generateMultiCounterOrderMap(List<Counter> counters) {
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
}
