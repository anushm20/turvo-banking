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

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.entities.TokenCounterQueue;
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
	
	public boolean serveToken(Counter counter, Token token, String action) {
		if(TokenStatus.COMPLETED.toString().equalsIgnoreCase(action)) {
			return completeToken(counter, token);
		} else if (TokenStatus.CANCELLED.toString().equalsIgnoreCase(action)) {
			return cancelToken(counter, token);
		} else if (TokenStatus.REVISIT.toString().equalsIgnoreCase(action)) {
			return revisitToken(counter, token);
		} else {
			return false;
		}
	}
	
	public boolean completeToken(Counter counter, Token token) {
		// Action Completed
		// Check how many counters customer has to go
		// more than 1. Set to inprogress
		// Fetch one more time
		Token dbToken = tokenService.getTokenById(token.getTokenId());
		if(dbToken.getCounters().size() > 1) {
			dbToken.setStatus(TokenStatus.INPROGRESS);
		} else {
			// Operator can add token comments here
			dbToken.setStatus(TokenStatus.COMPLETED);
			counter.getCounterQueue().remove(token);
		}
		// Remove current counter from the list
		TokenCounterQueue queue = null;
		Iterator<TokenCounterQueue> iterator = dbToken.getCounters().iterator();
		while(iterator.hasNext()) {
			queue = iterator.next();
			if(queue.getCounter().getCounterId() == counter.getCounterId()) {
				break;
			}
		}
		dbToken.getCounters().remove(queue);
		// Get the next counter where customer has to go
		if(dbToken.getCounters().size() > 0) {
			List<TokenCounterQueue> tokenList = new ArrayList<>(dbToken.getCounters());
			if(dbToken.getCounters().size() > 1) {
				Collections.sort(tokenList,new TokenCounterOrderComparator());
			}
			Counter nextCounter = counterService.getCounterById
					(tokenList.get(0).getCounter().getCounterId());
			nextCounter.getCounterQueue().add(token);
		} 
		boolean success = tokenService.updateToken(dbToken);
		return success;
	}
	
	public boolean cancelToken(Counter counter, Token token) {
		// Operator /Manager can mark the token cancelled due to some reason
		// Mark the status as Cancelled
		// Check the role
		token.setStatus(TokenStatus.CANCELLED);
		// Update token
		tokenService.updateToken(token);
		return true;
	}
	
	public boolean revisitToken(Counter counter, Token token) {
		// Operator can mark the token revisit if customer didn't appear
		// Mark the status as Revisit
		token.setStatus(TokenStatus.REVISIT);
		// Update token
		tokenService.updateToken(token);
		return true;
	}

}
