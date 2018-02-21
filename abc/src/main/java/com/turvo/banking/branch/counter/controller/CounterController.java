/**
 * Rest controller for Service counter
 */
package com.turvo.banking.branch.counter.controller;

import java.util.ArrayList;
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

import com.turvo.banking.branch.counter.entities.Counter;
import com.turvo.banking.branch.counter.operations.CounterOperations;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.Token;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to create service counters in branch of a bank")
public class CounterController {
	
	@Autowired
	CounterService counterService;
	
	@Autowired
	CounterOperations operations;
	
	@Autowired
	BranchServices branchServices; 
	
	@ApiOperation(value = "View a given Branch Counter", response = Counter.class)
	@GetMapping("/counter/{id}")
	public Counter getCounter(@PathVariable("id") Long id) {
		return counterService.getCounterById(id);
	}
	
	@ApiOperation(value = "Create a new Branch  Counter", response = HttpStatus.class)
	@PostMapping(path="/counters",consumes = "application/json")
	public ResponseEntity<Long> createCounter(@Valid @RequestBody Counter counter){
		Long id = counterService.createCounter(counter);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a Branch Counter", response = HttpStatus.class)
	@PutMapping("/counter/{id}")
	public HttpStatus updateCounter(@PathVariable("id") Long id,
				@Valid @RequestBody Counter counter){
		boolean success = counterService.updateCounter(counter);
		if(success)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@ApiOperation(value = "Delete a Branch Counter", response = HttpStatus.class)
	@DeleteMapping("/counter/{id}")
	public HttpStatus deleteBranchCounter(@PathVariable("id") Long id) {
		boolean success = counterService.deleteCounter(id);
		if(success)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@ApiOperation(value = "View list of tokens available at a "
			+ "Branch Counter", response = List.class)
	@GetMapping("/counter/{id}/tokens")
	public List<Integer> getTokensAtCounter(@PathVariable("id") Long id){
		List<Integer> tokens = new ArrayList<>();
		Counter counter = counterService.getCounterById(id);
		PriorityQueue<Token> queue = counter.getCounterQueue();
		queue.forEach((token)->tokens.add(token.getNumber()));
		return tokens;
	}
	
	@ApiOperation(value = "Take an action aganist a token in a "
			+ "	Branch Counter", response = HttpStatus.class)
	@GetMapping("/counter/{id}/token/{action}")
	public HttpStatus takeActionOnToken(@PathVariable("id") Long counterId,
			@PathVariable("action") String action) {
		Counter counter = counterService.getCounterById(counterId);
		Token token = counter.getCounterQueue().peek();
		boolean success = operations.serveToken(counter, token, action);
		if(success)
			return HttpStatus.OK;
		 else 
			return HttpStatus.BAD_REQUEST;
	}
	
}
