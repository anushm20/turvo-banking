/**
 * REST Controller for Customer Token
 */
package com.turvo.banking.branch.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.services.CustomerTokenService;

/**
 * @author anushm
 *
 */
@RestController
public class CustomerTokenController {
	
	@Autowired
	CustomerTokenService tokenService;
	
	@GetMapping("/token/{id}")
	public CustomerToken getCustomerToken(@PathVariable("id") Long tokenId) {
		return tokenService.getCustomerTokenByNumber(tokenId);
	}
	
	@PostMapping(path="/tokens",consumes = "application/json")
	public HttpStatus createCustomerToken(@RequestBody CustomerToken token){
		Long id = tokenService.createCustomerToken(token);
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/token/{id}")
	public HttpStatus updateCustomerToken(@PathVariable("id") Long id,
				@RequestBody CustomerToken token){
		tokenService.updateCustomerToken(token);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/token/{id}")
	public HttpStatus deleteCustomerToken(@PathVariable("id") Long id) {
		tokenService.deleteCustomerToken(id);
		return HttpStatus.OK;
	}
	
}
