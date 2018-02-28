/**
 * Operations that can be performed at a particular service counter
 * 
 * Performs necessary operations on the token in a service counter
 */
package com.turvo.banking.branch.operations;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.model.Token;
import com.turvo.banking.branch.model.TokenCounterMapper;
import com.turvo.banking.branch.model.TokenStatus;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.services.CounterService;
import com.turvo.banking.branch.services.TokenService;
import com.turvo.banking.branch.strategies.TokenHelper;
import com.turvo.banking.common.BankingConstants;

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
	
	@Autowired
	TokenHelper helper;

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
		} else if (TokenStatus.COUNTER_REVISIT.toString().equalsIgnoreCase(action)) {
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
		// Remove current counter from the list
		TokenCounterMapper mapper = getCurrentCounterMapper(counterId, token);
		token.getCounters().remove(mapper);
		// Update the token status to counter complete
		token.setStatus(TokenStatus.COUNTER_COMPLETE);
		// Add any token comments
		boolean success = tokenService.updateToken(token);
		helper.sendTokenToProcess(BankingConstants.TOKEN_COUNTER_EXCHANGE_QUEUE,
						token.getTokenId(),counterId);
		// Get the next counter where customer has to go
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
		token.setStatus(TokenStatus.COUNTER_REVISIT);
		// Update token
		tokenService.updateToken(token);
		return true;
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
