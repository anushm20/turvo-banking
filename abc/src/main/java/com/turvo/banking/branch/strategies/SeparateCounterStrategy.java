/**
 * Implementation class for separate counter strategies
 */
package com.turvo.banking.branch.strategies;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.model.BranchService;
import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.Token;
import com.turvo.banking.branch.model.TokenStatus;
import com.turvo.banking.branch.operations.CountersUtil;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.services.CounterService;
import com.turvo.banking.branch.services.TokenService;
import com.turvo.banking.common.BankingConstants;
import com.turvo.banking.exceptions.BankEntityNotFoundException;
import com.turvo.banking.exceptions.InvalidDataException;

/**
 * @author anushm
 *
 */
@Component(BankingConstants.SEPARATE_STRATEGY)
public class SeparateCounterStrategy implements CounterStrategyPicker {

	@Autowired
	TokenService tokenServices;
	
	@Autowired
	CountersUtil util;
	
	@Autowired
	CounterService counterService;

	@Autowired
	BranchServices branchServices;
	
	/**
	 * Method to place the token in first counter queue
	 * 1. Get the first from the list of services opted
	 * 2. For that service, find the counter and create the mapping
	 * 3. Save it to database
	 */
	@Override
	public boolean queueTokenAtFirstCounter(Token token) throws 
				InvalidDataException, BankEntityNotFoundException {
		// Since we are following separate counter strategy
		// Let's keep priority same as token number
		token.setPriority(token.getNumber());
		// Get the services list
		// Assign the token to first counter
		if(Objects.nonNull(token.getBranchServices()) &&
					token.getBranchServices().size() > 0) {
			Long serviceId = token.getBranchServices().get(0);  
			// Get the Branch Service Object
			token.setCounter(util.placeTokenInFirstCounter(serviceId, token));
		}
		// Assign the token
		token.setStatus(TokenStatus.QUEUED);
		// All counters are assigned in the order
		// save them now
		boolean success = tokenServices.updateToken(token);
		return success;
	}
	
	/**
	 * Method to process token to next stages . It follows below process :
	 * Check given service is multicounter.
	 * 		Yes 
	 * 			- Check all counters processed
	 * 				Yes - Move it to next service counter if any else mark it as complete
	 * 				No - Move it next counter for the same service
	 * 		No
	 * 			- Check all services done or not
	 * 				Yes - Mark the token complete
	 * 				No - Move it counter for the next service
	 */
	@Override
	public boolean processTokenToNextStages(Token token, Long counterId) 
					throws BankEntityNotFoundException {
		if (Objects.nonNull(token.getBranchServices()) && token.getBranchServices().size() > 0) {
			Counter counter = counterService.getCounterById(counterId);
			// Get Service object for this counter
			BranchService brService = branchServices.getBranchServiceById(counter.getBrServiceId());
			if (brService.getMultiCounter()) {
				Map<Integer, List<Counter>> counterOrderMap = util.generateMapForMultiCounter(token, brService);
				Integer nextOrder = util.findNextOrderedMultiCounterService(counter, counterOrderMap);
				if (nextOrder != null) {
					token.setCounter(util.getCounterFromList(counterOrderMap.get(nextOrder)));
				} else {
					moveTokenToNextCounterBasedOnServices(token, brService);
				}
			} else {
				moveTokenToNextCounterBasedOnServices(token, brService);
			}
			return true;
		} else {
			// throw exception
			return false;

		}
	}
	
	/**
	 * Method to move token to next counter after completion of a service
	 * @param token
	 * @param brService
	 * @throws BankEntityNotFoundException
	 */
	private void moveTokenToNextCounterBasedOnServices(Token token, BranchService brService)
			throws BankEntityNotFoundException {
		if (token.getBranchServices().size() == 1) {
			token.setStatus(TokenStatus.COMPLETED);
		} else {
			// This multicounter service is completed
			// get next service and create its mapping row
			boolean moved = util.placeTokenInNextServiceCounter(token, brService);
			if(!moved) {
				// means all services done
				//mark the status as completed
				token.setStatus(TokenStatus.COMPLETED);
			}
		}
	}
}
