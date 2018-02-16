/**
 * Operations that can be performed at a particular service counter
 * 
 * Performs necessary operations on the token in a service counter
 */
package com.turvo.banking.branch.counter.operations;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.counter.services.BranchCounterMappingServices;
import com.turvo.banking.branch.counter.services.BranchCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.branch.token.services.CustomerTokenService;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
@Component
public class BranchCounterOperator {
	
	@Autowired
	BranchCounterService counterService;
	
	@Autowired
	BranchCounterMappingServices branchCounterMappingServices;
	
	@Autowired
	CustomerTokenService tokenService;
	
	public void takeActiononTokeninCounter(Long counterId, CustomerToken token) {
		// Action Completed
		// Check for Multi-counter service & Customer Priority
		// For priority customer all services will be handled at one counter only
		BranchCounter counter = counterService.getServiceCounterById(counterId);
		List<Long> counters = branchCounterMappingServices.getServiceCountersForService(counter.getServiceId());
		if(Objects.nonNull(counters) && counters.size() > 1 && 
				CustomerType.REGULAR.toString().equalsIgnoreCase(token.getCustomerType())) {
			// Yes 
			Long nextCounter = 0L;
			boolean next = false;
			for(Long id : counters) {
				if(next) {
					nextCounter = id;
					break;
				}
				if(id == counterId) {
					next = true;
				}
			}
			// Insert the token into next priority queue 
			BranchCounter nextServiceCounter = counterService.getServiceCounterById(nextCounter);
			nextServiceCounter.getTokenQueue().add(token);
			//Remove the token at current queue
			counter.getTokenQueue().remove(token);
			token.setStatus(TokenStatus.INPROGRESS);
			token.setComments("");
			token.setComments(token.getComments()+"Service Completed at Counter :"+ counterId+".");
		} else {
			// No
			// Mark the status as complete
			token.setStatus(TokenStatus.COMPLETED);
			// Remove the token from counter Queue
			counter.getTokenQueue().remove(token);
			counterService.updateServiceCounter(counter);
		}
		// Update token 
		tokenService.updateCustomerToken(token);
		
	}
	
	public void cancelToken(Long counterId, CustomerToken token) {
		// Operator /Manager can mark the token cancelled due to some reason
		// Mark the status as Cancelled
		token.setStatus(TokenStatus.CANCELLED);
		// Update token
		tokenService.updateCustomerToken(token);
	}
	
	public void revisitToken(Long counterId, CustomerToken token) {
		// Operator can mark the token revisit if customer didn't appear
		// Mark the status as Revisit
		token.setStatus(TokenStatus.REVISIT);
		// Update token
		tokenService.updateCustomerToken(token);
	}

}
