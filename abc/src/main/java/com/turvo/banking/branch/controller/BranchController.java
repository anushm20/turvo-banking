/**
 * REST controller for Service to Service Counter Mapping
 */
package com.turvo.banking.branch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;
import com.turvo.banking.branch.services.BranchServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to map Service to "
		+ "service counters in branch of a bank")
public class BranchController {
	
	@Autowired
	BranchServices branchServices;

	@ApiOperation(value = "Get list of Service Counter Id's for a given bank service", response = List.class)
	@GetMapping("/countermapping/{id}")
	public List<Long> getServiceCountersForService(@PathVariable("id") Long serviceId) {
		return branchServices.getServiceCountersForService(serviceId);
	}
	
	@ApiOperation(value = "Map a service to a service counter in branch of a bank", response = HttpStatus.class)
	@PostMapping(path="/countermappings",consumes = "application/json")
	public HttpStatus createServiceCounterMapping
				(@RequestBody ServiceToServiceCounterMapping mapping){
		branchServices.createServiceToServiceCounterMapping(mapping);;
		return HttpStatus.CREATED;
	}
	
	@ApiOperation(value = "Update a service to a service counter in branch of a bank", response = HttpStatus.class)
	@PutMapping("/countermapping/{id}")
	public HttpStatus updateServiceCounterMapping(@PathVariable("id") Long serviceId,
				@RequestBody ServiceToServiceCounterMapping mapping){
		branchServices.updateServiceToServiceCounterMapping(mapping);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a service to a service counter in branch of a bank", response = HttpStatus.class)
	@DeleteMapping("/countermapping/{id}")
	public HttpStatus deleteServiceCounterMapping(@PathVariable("id") Long serviceId) {
		branchServices.deleteServiceToServiceCounterMapping(serviceId);
		return HttpStatus.OK;
	}
	
}
