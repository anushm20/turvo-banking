/**
 * 
 */
package com.turvo.banking.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.banking.customer.entities.Customer;
import com.turvo.banking.customer.services.CustomerService;

/**
 * @author anushm
 *
 */
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable("id") Long customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@PostMapping(path="/customers",consumes = "application/json")
	public HttpStatus createCustomerToken(@RequestBody Customer customer){
		Long id = customerService.createCustomer(customer);
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/customer/{id}")
	public HttpStatus updateCustomer(@PathVariable("id") Long id,
				@RequestBody Customer customer){
		customerService.updateCustomer(customer);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/customer/{id}")
	public HttpStatus deleteCustomer(@PathVariable("id") Long customerId) {
		customerService.deleteCustomer(customerId);
		return HttpStatus.OK;
	}

}
