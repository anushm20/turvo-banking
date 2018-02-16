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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.banking.branch.counter.entities.ServiceCounter;
import com.turvo.banking.branch.counter.operations.ServiceCounterOperator;
import com.turvo.banking.branch.counter.services.ServiceCounterService;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.entities.TokenStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to create service counters in branch of a bank")
public class ServiceCounterController {
	
	@Autowired
	ServiceCounterService counterService;
	
	@Autowired
	ServiceCounterOperator operator;
	
	@ApiOperation(value = "View a given Bank Service Counter", response = ServiceCounter.class)
	@GetMapping("/servicecounter/{id}")
	public ServiceCounter getServiceCounter(@PathVariable("id") Long id) {
		return counterService.getServiceCounterById(id);
	}
	
	@ApiOperation(value = "Create a new Bank Service Counter", response = HttpStatus.class)
	@PostMapping(path="/servicecounters",consumes = "application/json")
	public ResponseEntity<Long> createServiceCounter(@RequestBody ServiceCounter counter){
		Long id = counterService.createServiceCounter(counter);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a Bank Service Counter", response = HttpStatus.class)
	@PutMapping("/servicecounter/{id}")
	public HttpStatus updateServiceCounter(@PathVariable("id") Long id,
				@RequestBody ServiceCounter counter){
		ServiceCounter dbCounter = counterService.getServiceCounterById(id);
		counter.setTokenQueue(dbCounter.getTokenQueue());
		counterService.updateServiceCounter(counter);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a Bank Service Counter", response = HttpStatus.class)
	@DeleteMapping("/servicecounter/{id}")
	public HttpStatus deleteServiceCounter(@PathVariable("id") Long id) {
		counterService.deleteServiceCounter(id);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "View list of tokens available at a Service Counter", response = List.class)
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
	
	@ApiOperation(value = "Take an action aganist a token in a Service Counter", response = HttpStatus.class)
	@GetMapping("/servicecounter/{id}/token/{action}")
	public HttpStatus takeActionOnToken(@PathVariable("id") Long counterId,
			@PathVariable("action") String action) {
		PriorityQueue<CustomerToken> queue = counterService.getQueueForServiceCounter(counterId);
		CustomerToken token = queue.peek();
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
