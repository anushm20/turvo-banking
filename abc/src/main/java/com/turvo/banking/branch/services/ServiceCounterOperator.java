/**
 * Operations that can be performed at a particular service counter
 * 
 * Performs necessary operations on the token in a service counter
 */
package com.turvo.banking.branch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.counter.services.ServiceCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.branch.token.services.CustomerTokenService;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
@Component
public class ServiceCounterOperator {
	
	@Autowired
	ServiceCounterService counterService;
	
	@Autowired
	BranchServices branchServices;
	
	@Autowired
	CustomerTokenService tokenService;
	
	public void takeActiononTokeninCounter(Long counterId, CustomerToken token) {
		// Action Completed
		// Check for Multi-counter service & Customer Priority
		ServiceCounter counter = counterService.getServiceCounterById(counterId);
		List<Long> counters = branchServices.getServiceCountersForService(counter.getServiceId());
		if(counters.size() > 1 && CustomerType.REGULAR.toString().equalsIgnoreCase(token.getCustomerType())) {
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
			ServiceCounter nextServiceCounter = counterService.getServiceCounterById(nextCounter);
			nextServiceCounter.getTokenQueue().add(token);
			token.setComments(token.getComments()+"Service Completed at Counter :"+ counterId+".");
		} else {
			// No
			// Mark the status as complete
			token.setStatus(TokenStatus.COMPLETED);
			// Update token
			tokenService.updateCustomerToken(token);
			// Remove the token from counter Queue
			counter.getTokenQueue().remove(token);
			counterService.updateServiceCounter(counter);
		}
		
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
