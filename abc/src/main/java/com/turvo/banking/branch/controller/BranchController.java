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

/**
 * @author anushm
 *
 */
@RestController
public class BranchController {
	
	@Autowired
	BranchServices branchServices;

	@GetMapping("/countermapping/{id}")
	public List<Long> getServiceCountersForService(@PathVariable("id") Long serviceId) {
		return branchServices.getServiceCountersForService(serviceId);
	}
	
	@PostMapping(path="/countermappings",consumes = "application/json")
	public HttpStatus createServiceCounterMapping
				(@RequestBody ServiceToServiceCounterMapping mapping){
		branchServices.createServiceToServiceCounterMapping(mapping);;
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/countermapping/{id}")
	public HttpStatus updateServiceCounterMapping(@PathVariable("id") Long serviceId,
				@RequestBody ServiceToServiceCounterMapping mapping){
		branchServices.updateServiceToServiceCounterMapping(mapping);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/countermapping/{id}")
	public HttpStatus deleteServiceCounterMapping(@PathVariable("id") Long serviceId) {
		branchServices.deleteServiceToServiceCounterMapping(serviceId);
		return HttpStatus.OK;
	}
	
}
