/**
 * Rest controller for Service counter
 */
package com.turvo.banking.branch.counter.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.turvo.banking.branch.counter.operations.BranchCounterOperator;
import com.turvo.banking.branch.counter.services.CounterService;
import com.turvo.banking.branch.entities.BranchService;
import com.turvo.banking.branch.services.BranchServices;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.entities.TokenStatus;

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
	BranchCounterOperator operator;
	
	@Autowired
	BranchServices branchServices; 
	
	@ApiOperation(value = "View a given Branch Service Counter", response = Counter.class)
	@GetMapping("/servicecounter/{id}")
	public Counter getBranchCounter(@PathVariable("id") Long id) {
		return counterService.getCounterById(id);
	}
	
	@ApiOperation(value = "Create a new Branch Service Counter", response = HttpStatus.class)
	@PostMapping(path="/servicecounters",consumes = "application/json")
	public ResponseEntity<Long> createBranchCounter(@Valid @RequestBody Counter counter){
		//Get Branch Service object
		BranchService brService = branchServices.getBranchServiceById
				(counter.getBrService().getBranchServiceId());
		counter.setBrService(brService);
		Long id = counterService.createCounter(counter);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a Branch Service Counter", response = HttpStatus.class)
	@PutMapping("/servicecounter/{id}")
	public HttpStatus updateBranchCounter(@PathVariable("id") Long id,
				@Valid @RequestBody Counter counter){
		boolean success = counterService.updateCounter(counter);
		if(success)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@ApiOperation(value = "Delete a Branch Service Counter", response = HttpStatus.class)
	@DeleteMapping("/servicecounter/{id}")
	public HttpStatus deleteBranchCounter(@PathVariable("id") Long id) {
		boolean success = counterService.deleteCounter(id);
		if(success)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@ApiOperation(value = "View list of tokens available at a "
			+ "Branch Service Counter", response = List.class)
	@GetMapping("/servicecounter/{id}/tokens")
	public List<Integer> getTokensAtCounter(@PathVariable("id") Long id){
		List<Integer> tokens = new ArrayList<>();
		/*PriorityQueue<CustomerToken> queue = counterService.getQueueForServiceCounter(id);
		Iterator<CustomerToken> tokenItr = queue.iterator();
		while(tokenItr.hasNext())
			tokens.add(tokenItr.next().getNumber());
		Collections.sort(tokens);*/
		return tokens;
	}
	
	@ApiOperation(value = "Take an action aganist a token in a "
			+ "	Branch Service Counter", response = HttpStatus.class)
	@GetMapping("/servicecounter/{id}/token/{action}")
	public HttpStatus takeActionOnToken(@PathVariable("id") Long counterId,
			@PathVariable("action") String action) {
	//	PriorityQueue<CustomerToken> queue = counterService.getQueueForServiceCounter(counterId);
		Token token = new Token();
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
