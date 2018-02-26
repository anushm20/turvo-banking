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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.counter.strategies.CounterStrategyPicker;
import com.turvo.banking.branch.counter.strategies.CounterTokenAssignerFactory;
import com.turvo.banking.branch.entities.Branch;
import com.turvo.banking.branch.entities.BranchService;
import com.turvo.banking.branch.exceptions.BankEntityNotFoundException;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.services.BranchCrudService;
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
	
	@Autowired
	BranchCrudService branchCrudService;
	
	/**
	 * Method to place the token in the first counter for the 
	 * given serviceID
	 * @param serviceId
	 * @param token
	 * @return list of token counter mapper objects
	 * @throws InvalidDataException
	 * @throws BankEntityNotFoundException 
	 */
	public Set<TokenCounterMapper> placeTokenInFirstCounter(Long serviceId,Token token) 
					throws InvalidDataException, BankEntityNotFoundException {
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
			throw new BankEntityNotFoundException("Counters cannot be found for the service "+
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
	
	/**
	 * Method to return propery strategy picker based on branch type
	 * @param branchId
	 * @return strategypicker object
	 * @throws BankEntityNotFoundException
	 */
	public CounterStrategyPicker getStrategyPickerForBranch(Integer branchId) 
											throws BankEntityNotFoundException 	{
		Branch branch = branchCrudService.getBranchById(branchId);
		if(Objects.nonNull(branch)) {
			// Based on branch strategy pick strategy
			return CounterTokenAssignerFactory.
							getStrategyPicker(branch.getCounterStrategyType().toString());
		} else {
			throw new BankEntityNotFoundException("Customer Selected Branch is not available");
		}
	}
	
	/**
	 * Method to find next counter for the multi counter service
	 * @param counter
	 * @param counterOrderMap
	 * @return
	 */
	public Integer findNextOrderedMultiCounterService(Counter counter, Map<Integer, List<Counter>> counterOrderMap) {
		// Assumption is next counter order will be incremented by 1
		boolean next = false;
		Integer nextOrder = null;
		for (Integer order : counterOrderMap.keySet()) {
			if(next == true) {
				nextOrder =  order;
				break;
			}
			if(order == counter.getOrder()) {
				next =true;
			}
		}
		return nextOrder;
	}
	
	
	/**
	 * Method to generate map for multi counter service considering order
	 * @param token
	 * @param brService
	 * @return map
	 */
	public Map<Integer, List<Counter>> generateMapForMultiCounter(Token token, BranchService brService) {
		List<Counter> counters = null;
		try {
			counters = getListOfCountersForService
					(token, brService.getBranchServiceId());
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		Map<Integer, List<Counter>> counterOrderMap = generateMultiCounterOrderMap(counters);
		return counterOrderMap;
	}
	
	/**
	 * Method to place the token in next service counter
	 *  1. Process remaining services
	 *	2. find out the next service using current service object
	 *	3. Get the counter for that
	 *	4. Create a Mapping row
	 * @param token
	 * @param brService
	 * @throws BankEntityNotFoundException 
	 */
	public boolean placeTokenInNextServiceCounter(Token token, BranchService brService) 
					throws BankEntityNotFoundException {
		Long nextService = getNextServiceForToken(token, brService);
		if(Objects.nonNull(nextService)) {
			try {
				token.setCounters(placeTokenInFirstCounter(nextService, token));
			} catch (InvalidDataException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			// No next service is there
			// mark it as complete
			return false;
		}
	}
	
	/**
	 * Method to get the next service id for the token
	 * @param token
	 * @param brService
	 * @return service id
	 */
	private Long getNextServiceForToken(Token token, BranchService brService) {
		boolean next = false;
		Long nextService = null;
		for (Long serviceId : token.getBranchServices()) {
			if(next) {
				nextService = serviceId;
				break;
			}
			if(serviceId == brService.getBranchServiceId()) {
				next = true;
			}
		}
		return nextService;
	}

}
