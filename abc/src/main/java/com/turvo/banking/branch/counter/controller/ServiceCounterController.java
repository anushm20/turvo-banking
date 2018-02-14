/**
 * Rest controller for Service counter
 */
package com.turvo.banking.branch.counter.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.counter.services.ServiceCounterService;
import com.turvo.banking.branch.services.CustomerTokenComparator;
import com.turvo.banking.branch.services.ServiceCounterOperator;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.entities.TokenStatus;

/**
 * @author anushm
 *
 */
@RestController
public class ServiceCounterController {
	
	@Autowired
	ServiceCounterService counterService;
	
	@Autowired
	ServiceCounterOperator operator;
	
	@GetMapping("/servicecounter/{id}")
	public ServiceCounter getServiceCounter(@PathVariable("id") Long id) {
		return counterService.getServiceCounterById(id);
	}
	
	@PostMapping(path="/servicecounters",consumes = "application/json")
	public HttpStatus createServiceCounter(@RequestBody ServiceCounter counter){
		// Set priority Queue for each counter
		PriorityQueue<CustomerToken> queue = new PriorityQueue<>(new CustomerTokenComparator());
		counter.setTokenQueue(queue);
		Long id = counterService.createServiceCounter(counter);
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/servicecounter/{id}")
	public HttpStatus updateServiceCounter(@PathVariable("id") Long id,
				@RequestBody ServiceCounter counter){
		ServiceCounter dbCounter = counterService.getServiceCounterById(id);
		counter.setTokenQueue(dbCounter.getTokenQueue());
		counterService.updateServiceCounter(counter);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/servicecounter/{id}")
	public HttpStatus deleteServiceCounter(@PathVariable("id") Long id) {
		counterService.deleteServiceCounter(id);
		return HttpStatus.OK;
	}
	
	@GetMapping("/servicecounter/{id}/tokens")
	public List<Long> getTokensAtCounter(@PathVariable("id") Long id){
		List<Long> tokens = new ArrayList<>();
		PriorityQueue<CustomerToken> queue = counterService.getQueueForServiceCounter(id);
		Iterator<CustomerToken> tokenItr = queue.iterator();
		while(tokenItr.hasNext())
			tokens.add(tokenItr.next().getNumber());
		Collections.sort(tokens);
		return tokens;
	}
	
	@GetMapping("/servicecounter/{id}/token/{action}")
	public HttpStatus takeActionOnToken(@PathVariable("id") Long counterId,
			@PathVariable("action") String action,@RequestBody CustomerToken token) {
		if(TokenStatus.COMPLETED.toString().equalsIgnoreCase(action)) {
			operator.takeActiononTokeninCounter(counterId, token);
			return HttpStatus.OK;
		} else if (TokenStatus.CANCELLED.toString().equalsIgnoreCase(action)) {
			operator.cancelToken(counterId, token);
			return HttpStatus.OK;
		} else if (TokenStatus.REVISIT.toString().equalsIgnoreCase(action)) {
			operator.revisitToken(counterId, token);
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
}
