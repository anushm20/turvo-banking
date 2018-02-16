/**
 * REST Controller for Customer Token
 */
package com.turvo.banking.branch.token.controller;

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

import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.services.CustomerTokenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to create customer tokens in a bank")
public class CustomerTokenController {
	
	@Autowired
	CustomerTokenService tokenService;
	
	@ApiOperation(value = "View a customer Token Details", response = CustomerToken.class)
	@GetMapping("/token/{id}")
	public CustomerToken getCustomerToken(@PathVariable("id") Long tokenId) {
		return tokenService.getCustomerTokenByNumber(tokenId);
	}
	
	@ApiOperation(value = "Create a new Customer token", response = HttpStatus.class)
	@PostMapping(path="/tokens",consumes = "application/json")
	public ResponseEntity<Long> createCustomerToken(@Valid @RequestBody CustomerToken token){
		Long id = tokenService.createCustomerToken(token);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a new Customer token", response = HttpStatus.class)
	@PutMapping("/token/{id}")
	public HttpStatus updateCustomerToken(@PathVariable("id") Long id,
				@Valid @RequestBody CustomerToken token){
		tokenService.updateCustomerToken(token);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a new Customer token", response = HttpStatus.class)
	@DeleteMapping("/token/{id}")
	public HttpStatus deleteCustomerToken(@PathVariable("id") Long id) {
		tokenService.deleteCustomerToken(id);
		return HttpStatus.OK;
	}
	
}
