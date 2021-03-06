/**
 * REST Controller for Bank Services
 */
package com.turvo.banking.bank.controller;

import java.util.List;

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

import com.turvo.banking.bank.entities.BankService;
import com.turvo.banking.bank.services.BankServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anushm
 *
 */
@RestController
@Api(description="API's to create services in a bank")
public class BankServiceController {
	
	@Autowired
	BankServices bankServices;
	
	@ApiOperation(value = "View a list of available bank services", response = List.class)
	@GetMapping("/bankservices")
	public List<BankService> getAllBankServices() {
		return bankServices.getAllBankServices();
	}

	@ApiOperation(value = "View a given Bank Service", response = BankService.class)
	@GetMapping("/bankservices/{id}")
	public BankService getBankService(@PathVariable("id") Long id) {
		return bankServices.getBankServiceById(id);
	}
	
	@ApiOperation(value = "Create a new Bank Service", response = HttpStatus.class)
	@PostMapping(path="/bankservices",consumes = "application/json")
	public ResponseEntity<Long> createBankService(@RequestBody BankService service){
		Long id = bankServices.createBankService(service);
		return new ResponseEntity<Long>(id,HttpStatus.CREATED);
	}
	
	@PutMapping("/bankservices/{id}")
	@ApiOperation(value = "Update a Bank Service", response = HttpStatus.class)
	public HttpStatus updateBankService(@PathVariable("id") Long id,
				@RequestBody BankService service){
		bankServices.updateBankService(service);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/bankservices/{id}")
	@ApiOperation(value = "Delete a Bank Service", response = HttpStatus.class)
	public HttpStatus deleteBankService(@PathVariable("id") Long id) {
		bankServices.deleteBankService(id);
		return HttpStatus.OK;
	}
}
