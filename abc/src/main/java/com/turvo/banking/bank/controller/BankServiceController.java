/**
 * REST Controller for Bank Services
 */
package com.turvo.banking.bank.controller;

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

import com.turvo.banking.bank.entities.BankService;
import com.turvo.banking.bank.services.BankServices;

/**
 * @author anushm
 *
 */
@RestController
public class BankServiceController {
	
	@Autowired
	BankServices bankServices;
	
	@GetMapping("/bankservices")
	public List<BankService> getAllBankServices() {
		return bankServices.getAllBankServices();
	}

	@GetMapping("/bankservices/{id}")
	public BankService getBankService(@PathVariable("id") Long id) {
		return bankServices.getBankServiceById(id);
	}
	
	@PostMapping(path="/bankservices",consumes = "application/json")
	public HttpStatus createBankService(@RequestBody BankService service){
		Long id = bankServices.createBankService(service);
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/bankservices/{id}")
	public HttpStatus updateBankService(@PathVariable("id") Long id,
				@RequestBody BankService service){
		bankServices.updateBankService(service);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/bankservices/{id}")
	public HttpStatus deleteBankService(@PathVariable("id") Long id) {
		bankServices.deleteBankService(id);
		return HttpStatus.OK;
	}
	
	
}
