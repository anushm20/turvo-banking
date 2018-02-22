/**
 * Implementation class for separate counter strategies
 */
package com.turvo.banking.branch.counter.strategies;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.TokenCounterMapper;
import com.turvo.banking.branch.counter.operations.CountersUtil;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.exceptions.InvalidDataException;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.branch.token.services.TokenService;
import com.turvo.banking.common.BankingConstants;

/**
 * @author anushm
 *
 */
@Component(BankingConstants.SEPARATE_STRATEGY)
public class SeparateCounterStrategy implements CounterStrategyPicker {

	@Autowired
	CounterService counterServices;

	@Autowired
	BranchServices branchServices;

	@Autowired
	TokenService tokenServices;
	
	@Autowired
	CountersUtil util;

	@Override
	public boolean updateCounterQueue(Token token) throws 
				InvalidDataException, EntityNotFoundException {
		Set<TokenCounterMapper> tokenCounters = new HashSet<>();
		// Since we are following separate counter strategy
		// Let's keep priority same as token number
		token.setPriority(token.getNumber());
		// Get the services list
		// Assign the token to first counter
		if(Objects.nonNull(token.getBranchServices()) &&
					token.getBranchServices().size() > 0) {
			Long serviceId = token.getBranchServices().get(0);  
			// Get the Branch Service Object
			tokenCounters.addAll(util.placeTokenInFirstCounter(serviceId, token));
		}
		// Assign the token
		token.setCounters(tokenCounters);
		token.setStatus(TokenStatus.QUEUED);
		// All counters are assigned in the order
		// save them now
		boolean success = tokenServices.updateToken(token);
		return success;
	}
}
