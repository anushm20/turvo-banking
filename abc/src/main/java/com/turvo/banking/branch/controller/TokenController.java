/**
 * REST Controller for Token
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

import com.turvo.banking.branch.model.Token;
import com.turvo.banking.branch.services.TokenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to create tokens in a branch for Queue Management")
public class TokenController {
	
	@Autowired
	TokenService tokenService;
	
	@ApiOperation(value = "View a Token Details", response = Token.class)
	@GetMapping("/token/{id}")
	public Token getToken(@PathVariable("id") Long number) {
		return tokenService.getTokenById(number);
	}
	
	@ApiOperation(value = "Create a new token", response = HttpStatus.class)
	@PostMapping(path="/tokens",consumes = "application/json")
	public ResponseEntity<Integer> createToken(@Valid @RequestBody Token token){
		int id = tokenService.createToken(token);
		return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update a new token", response = HttpStatus.class)
	@PutMapping("/token/{id}")
	public HttpStatus updateToken(@PathVariable("id") Long id,
				@Valid @RequestBody Token token){
		tokenService.updateToken(token);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a new token", response = HttpStatus.class)
	@DeleteMapping("/token/{id}")
	public HttpStatus deleteToken(@PathVariable("id") Long tokenId) {
		tokenService.deleteToken(tokenId);
		return HttpStatus.OK;
	}
	
}
