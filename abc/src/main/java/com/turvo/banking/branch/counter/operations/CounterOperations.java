/**
 * Operations that can be performed at a particular service counter
 * 
 * Performs necessary operations on the token in a service counter
 */
package com.turvo.banking.branch.counter.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.comparators.TokenCounterOrderComparator;
import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.branch.counter.services.CounterService;
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

	/**
	 * Method to take action on the token based on the requested status
	 * 
	 * @param counter
	 * @param token
	 * @param action
	 * @return success or failure
	 */
	public boolean serveToken(Counter counter, Token token, String action) {
		if (TokenStatus.COMPLETED.toString().equalsIgnoreCase(action)) {
			return completeToken(counter, token);
		} else if (TokenStatus.CANCELLED.toString().equalsIgnoreCase(action)) {
			return cancelToken(counter, token);
		} else if (TokenStatus.REVISIT.toString().equalsIgnoreCase(action)) {
			return revisitToken(counter, token);
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
	public boolean completeToken(Counter counter, Token token) {
		// Action Completed
		// Check how many counters customer has to go
		// more than 1. Set to inprogress
		markTokenStatus(token);
		// Remove the token from current queue
		counter.getCounterQueue().remove(token);
		// Remove current counter from the list
		TokenCounterMapper mapper = getCurrentCounterMapper(counter, token);
		token.getCounters().remove(mapper);
		// Get the next counter where customer has to go
		boolean moved = moveTokenToNextCounter(token);
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
	public boolean cancelToken(Counter counter, Token token) {
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
	public boolean revisitToken(Counter counter, Token token) {
		// Operator can mark the token revisit if customer didn't appear
		// Mark the status as Revisit
		token.setStatus(TokenStatus.REVISIT);
		// Update token
		tokenService.updateToken(token);
		return true;
	}
	
	/**
	 * Move the token to next counter based on the counter list
	 * @param token
	 */
	private boolean moveTokenToNextCounter(Token token) {
		if (token.getCounters().size() > 0) {
			List<TokenCounterMapper> tokenList = new ArrayList<>(token.getCounters());
			if (token.getCounters().size() > 1) {
				Collections.sort(tokenList, new TokenCounterOrderComparator());
			}
			Counter nextCounter = counterService.getCounterById
					(tokenList.get(0).getCounter().getCounterId());
			nextCounter.getCounterQueue().add(token);
			return true;
		} else {
			// He is done with all services
			// No more counters are there
			return true;
		}
	}
	
	/**
	 * Method to set the proper token status
	 * @param token
	 */
	private void markTokenStatus(Token token) {
		if (token.getCounters().size() > 1) {
			token.setStatus(TokenStatus.INPROGRESS);
		} else {
			// Operator can add token comments here
			token.setStatus(TokenStatus.COMPLETED);
		}
	}

	/**
	 * Method to get current counter mapper from the list of counter mappers
	 * 
	 * @param counter
	 * @param dbToken
	 * @return current token counter mapper object
	 */
	private TokenCounterMapper getCurrentCounterMapper(Counter counter, Token dbToken) {
		TokenCounterMapper queue = null;
		Iterator<TokenCounterMapper> iterator = dbToken.getCounters().iterator();
		while (iterator.hasNext()) {
			queue = iterator.next();
			if (queue.getCounter().getCounterId() == counter.getCounterId()) {
				break;
			}
		}
		return queue;
	}

}
