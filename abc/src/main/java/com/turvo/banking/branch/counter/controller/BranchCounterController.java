/**
 * Rest controller for Service counter
 */
package com.turvo.banking.branch.counter.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import javax.validation.Valid;

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

import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.counter.operations.BranchCounterOperator;
import com.turvo.banking.branch.counter.services.BranchCounterService;
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
public class BranchCounterController {
	
	@Autowired
	BranchCounterService counterService;
	
	@Autowired
	BranchCounterOperator operator;
	
	@ApiOperation(value = "View a given Branch Service Counter", response = BranchCounter.class)
	@GetMapping("/servicecounter/{id}")
	public BranchCounter getBranchCounter(@PathVariable("id") Long id) {
		return counterService.getServiceCounterById(id);
	}
	
	@ApiOperation(value = "Create a new Branch Service Counter", response = HttpStatus.class)
	@PostMapping(path="/servicecounters",consumes = "application/json")
	public ResponseEntity<Long> createBranchCounter(@Valid @RequestBody BranchCounter counter){
		Long id = counterService.createServiceCounter(counter);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a Branch Service Counter", response = HttpStatus.class)
	@PutMapping("/servicecounter/{id}")
	public HttpStatus updateBranchCounter(@PathVariable("id") Long id,
				@Valid @RequestBody BranchCounter counter){
		BranchCounter dbCounter = counterService.getServiceCounterById(id);
		counter.setTokenQueue(dbCounter.getTokenQueue());
		counterService.updateServiceCounter(counter);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a Branch Service Counter", response = HttpStatus.class)
	@DeleteMapping("/servicecounter/{id}")
	public HttpStatus deleteBranchCounter(@PathVariable("id") Long id) {
		counterService.deleteServiceCounter(id);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "View list of tokens available at a "
			+ "Branch Service Counter", response = List.class)
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
	
	@ApiOperation(value = "Take an action aganist a token in a "
			+ "	Branch Service Counter", response = HttpStatus.class)
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