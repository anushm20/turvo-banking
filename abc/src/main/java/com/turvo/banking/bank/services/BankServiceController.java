/**
 * REST Controller for Bank Services
 */
package com.turvo.banking.bank.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.turvo.banking.bank.entities.BankService;

/**
 * @author anushm
 *
 */
@RestController("/bankservices")
public class BankServiceController {
	
	@Autowired
	BankServices bankServices;

	@GetMapping("/{id}")
	public BankService getBankService(@PathVariable("id") Long id) {
		return bankServices.getBankServiceById(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> createBankService(@RequestBody BankService service){
		Long id = bankServices.createBankService(service);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBankService(@PathVariable("id") Long id,
				@RequestBody BankService service){
		bankServices.updateBankService(service);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteBankService(@PathVariable("id") Long id) {
		bankServices.deleteBankService(id);
	}
	
	
}
