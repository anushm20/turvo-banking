/**
 * Validator for bank Service 
 */
package com.turvo.banking.bank.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author anushm
 *
 */
public class BankServiceValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	
}
