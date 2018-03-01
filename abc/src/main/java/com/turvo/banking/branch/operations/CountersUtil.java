/**
 * 
 */
package com.turvo.banking.branch.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.model.Branch;
import com.turvo.banking.branch.model.BranchService;
import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.CounterType;
import com.turvo.banking.branch.model.Token;
import com.turvo.banking.branch.services.BranchModelService;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.services.CounterService;
import com.turvo.banking.branch.strategies.CounterStrategyPicker;
import com.turvo.banking.branch.strategies.CounterTokenAssignerFactory;
import com.turvo.banking.exceptions.BankEntityNotFoundException;
import com.turvo.banking.exceptions.InvalidDataException;

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
	BranchModelService branchModelService;
	
	/**
	 * Method to place the token in the first counter for the 
	 * given serviceID
	 * @param serviceId
	 * @param token
	 * @return list of token counter mapper objects
	 * @throws InvalidDataException
	 * @throws BankEntityNotFoundException 
	 */
	public Counter placeTokenInFirstCounter(Long serviceId,Token token) 
					throws InvalidDataException, BankEntityNotFoundException {
		BranchService brService = branchServices.getBranchServiceById(serviceId);
		// Get the counters for the services based on token type
		List<Counter> counters = getListOfCountersForService(token, serviceId);
		if (Objects.nonNull(counters) && counters.size() > 0) {
			if (brService.getMultiCounter()) {
				// Assign counter for Multi counter service
				// Generate Map of counter order to counter object
				// For each order pass it to the method to get counter
				return assignCounterForMultiCounterService
							(token, counters);
			} else {
				// Assign counter for single counter service
				return getCounterFromList(counters);
			}
		} else {
			throw new BankEntityNotFoundException("Counters cannot be found for the service "+
						serviceId+" for customer type :"+token.getCustomer().getType());
		}
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
	 * @return the first counter for the service where customer has to go
	 */
	private Counter assignCounterForMultiCounterService(Token token, 
			List<Counter> counters) {
		Map<Integer, List<Counter>> counterOrderMap = 
				generateMultiCounterOrderMap(counters);
		for (Integer order : counterOrderMap.keySet()) {
			return getCounterFromList(counterOrderMap.get(order));
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
		}
	}
	
	/**
	 * Method to return proper strategy picker based on branch type
	 * @param branchId
	 * @return strategypicker object
	 * @throws BankEntityNotFoundException
	 */
	public CounterStrategyPicker getStrategyPickerForBranch(Integer branchId) 
											throws BankEntityNotFoundException 	{
		Branch branch = branchModelService.getBranchById(branchId);
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
	public Integer findNextOrderedMultiCounterService(Counter counter,
						Map<Integer, List<Counter>> counterOrderMap) {
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
	public Map<Integer, List<Counter>> generateMapForMultiCounter(Token token, 
							BranchService brService) {
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
				token.setCounter(placeTokenInFirstCounter(nextService, token));
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
