/**
 * 
 */
package com.turvo.banking.customer.controller;

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

import com.turvo.banking.customer.entities.Customer;
import com.turvo.banking.customer.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to create customers in a bank")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@ApiOperation(value = "View a Customer Details", response = Customer.class)
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable("id") Long customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@ApiOperation(value = "Create a new Customer", response = HttpStatus.class)
	@PostMapping(path="/customers",consumes = "application/json")
	public ResponseEntity<Long> createCustomer(@RequestBody Customer customer){
		Long id = customerService.createCustomer(customer);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update Customer details", response = HttpStatus.class)
	@PutMapping("/customer/{id}")
	public HttpStatus updateCustomer(@PathVariable("id") Long id,
				@RequestBody Customer customer){
		customerService.updateCustomer(customer);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a Customer", response = HttpStatus.class)
	@DeleteMapping("/customer/{id}")
	public HttpStatus deleteCustomer(@PathVariable("id") Long customerId) {
		customerService.deleteCustomer(customerId);
		return HttpStatus.OK;
	}

}
