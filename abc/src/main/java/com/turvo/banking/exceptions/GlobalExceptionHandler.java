/**
 * Global exception handler class to handle all exceptions 
 */
package com.turvo.banking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author anushm
 *
 */
@RestControllerAdvice
@RequestMapping(produces = "application/json")
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BankEntityNotFoundException.class)
	public ResponseEntity<Object> handleBankEntityNotFoundException(
			final BankEntityNotFoundException exe){
		return new ResponseEntity<>(exe.getMessage(),HttpStatus.NOT_FOUND);
	}

}
