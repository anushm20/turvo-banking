/**
 * Operations that can be performed at a particular service counter
 * 
 * Performs necessary operations on the token in a service counter
 */
package com.turvo.banking.branch.counter.operations;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.entities.BranchService;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.branch.token.services.TokenService;

/**
 * @author anushm
 *
 */
@Component
public class CounterOperations {

	@Autowired
	CounterService counterService;

	@Autowired
	TokenService tokenService;
	
	@Autowired
	BranchServices branchServices;
	
	@Autowired
	CountersUtil util;

	/**
	 * Method to take action on the token based on the requested status
	 * 
	 * @param counter
	 * @param token
	 * @param action
	 * @return success or failure
	 */
	public boolean serveToken(Long counterId, Token token, String action) {
		if (TokenStatus.COMPLETED.toString().equalsIgnoreCase(action)) {
			return completeToken(counterId, token);
		} else if (TokenStatus.CANCELLED.toString().equalsIgnoreCase(action)) {
			return cancelToken(counterId, token);
		} else if (TokenStatus.REVISIT.toString().equalsIgnoreCase(action)) {
			return revisitToken(counterId, token);
		} else {
			return false;
		}
	}

	/**
	 * Mark the token as complete at the current counter 
	 * and move it to next queue
	 * if required
	 * 
	 * @param counter
	 * @param token
	 * @return success or failure
	 */
	public boolean completeToken(Long counterId, Token token) {
		// Action Completed
		// Check how many counters customer has to go
		// Remove current counter from the list
		TokenCounterMapper mapper = getCurrentCounterMapper(counterId, token);
		token.getCounters().remove(mapper);
		// Get the next counter where customer has to go
		boolean moved = moveTokenToNextCounter(counterId,token);
		boolean success = false;
		if(moved) {
			success = tokenService.updateToken(token);
		} else {
			// Re try Mechanism should go here
		}
		return success;
	}
	
	/**
	 * Mark the token as cancelled
	 * 
	 * @param counter
	 * @param token
	 * @return success or failure
	 */
	public boolean cancelToken(Long counterId, Token token) {
		// Operator /Manager can mark the token cancelled due to some reason
		// Mark the status as Cancelled
		// Check the role
		token.setStatus(TokenStatus.CANCELLED);
		// Update token
		tokenService.updateToken(token);
		return true;
	}

	/**
	 * Mark the token as revisit
	 * 
	 * @param counter
	 * @param token
	 * @return success or failure
	 */
	public boolean revisitToken(Long counterId, Token token) {
		// Operator can mark the token revisit if customer didn't appear
		// Mark the status as Revisit
		token.setStatus(TokenStatus.REVISIT);
		// Update token
		tokenService.updateToken(token);
		return true;
	}
	
	/**
	 * Move the token to next counter based on the counter list
	 * @param counterId 
	 * @param token
	 */
	private boolean moveTokenToNextCounter(Long counterId, Token token) {
		// New logic
		if(Objects.nonNull(token.getBranchServices()) &&
				token.getBranchServices().size() > 0) {
			Counter counter = counterService.getCounterById(counterId);
			BranchService brService = branchServices.getBranchServiceById
					(counter.getBrServiceId());
			// Set the token Status to in Progress
			// Get Service object for this counter
			// Size 1.Not Multicounter
			// Find out the current service
			if(brService.getMultiCounter()) {
				Map<Integer, List<Counter>> counterOrderMap = generateMapForMultiCounter(token, brService);
				Integer nextOrder = findNextOrderedMultiCounterService(counter, counterOrderMap);
				if(nextOrder != null) {
					Counter nextCounter = util.getCounterFromList(counterOrderMap.get(nextOrder));
					token.getCounters().add(new TokenCounterMapper
							(token, nextCounter));
				} else {
					if(token.getBranchServices().size() == 1) {
						token.setStatus(TokenStatus.COMPLETED);
					} else {
						// Process remaining services
						// find out the next service
						// get the counter
						// create a row
						placeTokenInNextServiceCounter(token, brService);
					}
				}
			} else {
				// Single counter service
				if(token.getBranchServices().size() == 1) {
					token.setStatus(TokenStatus.COMPLETED);
				} else {
					placeTokenInNextServiceCounter(token, brService);
				}
			}
			return true;
		} else {
			// throw exception
			return false;

		}
	}

	/**
	 * Method to place the token in next service counter
	 * @param token
	 * @param brService
	 */
	private void placeTokenInNextServiceCounter(Token token, BranchService brService) {
		Long nextService = getNextServiceForToken(token, brService);
		try {
			token.setCounters(util.placeTokenInFirstCounter(nextService, token));
		} catch (InvalidDataException e) {
			e.printStackTrace();
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

	/**
	 * Method to generate map for multi counter service considering order
	 * @param token
	 * @param brService
	 * @return map
	 */
	private Map<Integer, List<Counter>> generateMapForMultiCounter(Token token, BranchService brService) {
		List<Counter> counters = null;
		try {
			counters = util.getListOfCountersForService
					(token, brService.getBranchServiceId());
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		Map<Integer, List<Counter>> counterOrderMap = util.generateMultiCounterOrderMap(counters);
		return counterOrderMap;
	}

	/**
	 * Method to find next counter for the multi counter service
	 * @param counter
	 * @param counterOrderMap
	 * @return
	 */
	private Integer findNextOrderedMultiCounterService(Counter counter, Map<Integer, List<Counter>> counterOrderMap) {
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
	 * Method to get current counter mapper from the list of counter mappers
	 * 
	 * @param counter
	 * @param dbToken
	 * @return current token counter mapper object
	 */
	private TokenCounterMapper getCurrentCounterMapper(Long counterId, Token dbToken) {
		TokenCounterMapper queue = null;
		Iterator<TokenCounterMapper> iterator = dbToken.getCounters().iterator();
		while (iterator.hasNext()) {
			queue = iterator.next();
			if (queue.getCounter().getCounterId() == counterId) {
				break;
			}
		}
		return queue;
	}
}
