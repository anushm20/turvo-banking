/**
 * Implementation class for regular Service counter Queue update
 */
package com.turvo.banking.branch.counter.operations;

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

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.entities.TokenCounterQueue;
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
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.counter.operations.CounterStrategyPicker#
	 * updateServiceCounterQueue(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public void updateServiceCounterQueue(Token token) {
		Set<TokenCounterQueue> tokenCounters = new HashSet<>();
		// Since we are following separate counter strategy
		//Let's keep priority same as token number
		token.setPriority(token.getNumber());
		int counterOrder = 0;
		// Get the services list
		for (Long serviceId : token.getBranchServices()) {
			// Get the Branch Service Object
			BranchService brService = branchServices.getBranchServiceById(serviceId); 
			// Get the counters for the services based on token type
			List<Counter> counters = new ArrayList<>();
			if(CounterType.PREMIUM.toString().equals
						(token.getCustomer().getType().toString())) {
				// Assumption is if branch chooses SEPARATE strategy there 
				// should be premium counters for each service
				counters.addAll(counterServices.getCountersByServiceAndType
						(serviceId, CounterType.PREMIUM));
			} else if(CounterType.REGULAR.toString().equals
					(token.getCustomer().getType().toString())) {
				counters.addAll(counterServices.getCountersByServiceAndType
						(serviceId, CounterType.REGULAR));
			} else {
				// TODO Throw exception
			}
			if(Objects.nonNull(counters) && counters.size()  > 0) {
				if(brService.getMultiCounter()) {
					// Generate Map of counter order to counter object
					// For each order pass it to the method to get counter
					Map<Integer, List<Counter>> counterOrderMap = 
							generateMultiCounterOrderMap(counters);
					for (Integer order : counterOrderMap.keySet()) {
						Counter counter = getCounterFromList
								(counterOrderMap.get(order));
						TokenCounterQueue  queue = new TokenCounterQueue();
						queue.setCounterId(counter);
						queue.setToken(token);
						queue.setOrder(counterOrder);
						counterOrder++;
						tokenCounters.add(queue);
					}
				} else {
					Counter counter = getCounterFromList(counters);
					TokenCounterQueue  queue = new TokenCounterQueue();
					queue.setCounterId(counter);
					queue.setToken(token);
					queue.setOrder(counterOrder);
					tokenCounters.add(queue);
					counterOrder++;
				}
			} else {
				// TODO throw exception
			}
		}
		// Assign the token
		token.setCounters(tokenCounters);
		token.setStatus(TokenStatus.QUEUED);
		// All counters are assigned in the order
		// save them now
		boolean success = tokenServices.updateToken(token);
	}

/*	private void getCountersForMultiCounterService(List<Counter> tokenCounters,
			List<Counter> counters) {
		Map<Integer, List<Counter>> counterOrderMap = 
				generateMultiCounterOrderMap(counters);
		for (Integer order : counterOrderMap.keySet()) {
			tokenCounters.add(getCounterFromList
						(counterOrderMap.get(order)));
		}
	}*/
	
	/*private void getCountersForMultiCounterService(List<Long> tokenCounters,
			List<Counter> counters) {
		Map<Integer, List<Counter>> counterOrderMap = 
				generateMultiCounterOrderMap(counters);
		for (Integer order : counterOrderMap.keySet()) {
			Counter counter = getCounterFromList
					(counterOrderMap.get(order));
			tokenCounters.add(counter.getCounterId());
		}
	}*/
	
	private Counter getCounterFromList(List<Counter> counters) {
		// if size is 1 return the counter
		if(counters.size() == 1) {
			return counters.get(0);
		} else {
			// more than 1 load balance them
			return loadBalanceCounters(counters);
		}
	}
	
	private Counter loadBalanceCounters(List<Counter> counters) {
		// Try to load balance the tokens
		// Initialize the token list if it is null
		/*counters.forEach((counter)->{
				if(Objects.isNull(counter.getTokens())) {
					counter.setTokens(new ArrayList<Token>());
				}
		});*/
		Collections.sort(counters,new CounterTokenSizeComparator());
		/*for (Counter counter : counters) {
			if(counter.getCapacity() == counter.getTokens().size())
				continue;
			else 
				return counter;
		}*/
		// Worst case return the first one
		return counters.get(0);
	}
	
	private Map<Integer,List<Counter>> generateMultiCounterOrderMap
			(List<Counter> counters){
		Map<Integer, List<Counter>> counterOrderMap = new TreeMap<>();
		for (Counter counter : counters) {
			if(Objects.nonNull(counterOrderMap.get(counter.getOrder()))) {
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
