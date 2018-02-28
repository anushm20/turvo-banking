/**
 * Contains rest end points for mapping a bank service
 * to a branch
 */
package com.turvo.banking.branch.controller;

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

import com.turvo.banking.bank.model.BankService;
import com.turvo.banking.bank.services.BankServices;
import com.turvo.banking.branch.model.BranchService;
import com.turvo.banking.branch.services.BranchServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to map services of a bank to a branch")
public class BranchServiceController {
	
	@Autowired
	BranchServices branchServices;
	
	@Autowired
	BankServices bankServices;
	
	@ApiOperation(value = "Get Mapping of a service to a branch", response = BranchService.class)
	@GetMapping("/branchservice/{id}")
	public BranchService getBranchService(@PathVariable("id") Long id) {
		return branchServices.getBranchServiceById(id);
	}
	
	@ApiOperation(value = "Create a new mapping of service to a Branch")
	@PostMapping(path="/branchservices",consumes = "application/json")
	public ResponseEntity<Long> createBranchService(@Valid @RequestBody BranchService service){
		BankService bankService = bankServices.getBankServiceById
				(service.getService().getServiceId());
		service.setService(bankService);
		Long id = branchServices.createBranchService(service);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a mapping of service to a Branch")
	@PutMapping("/branchservice/{id}")
	public HttpStatus updateBranchService(@PathVariable("id") Long id,
				@Valid @RequestBody BranchService service){
		boolean success = branchServices.updateBranchService(service);
		if(success)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@ApiOperation(value = "Delete a mapping of service to a Branch")
	@DeleteMapping("/branchservice/{id}")
	public HttpStatus deleteBranchService(@PathVariable("id") Long id) {
		boolean success = branchServices.deleteBranchService(id);
		if(success)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}

}
