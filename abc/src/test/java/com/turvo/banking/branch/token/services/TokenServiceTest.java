/**
 * Test Class for Customer Token Service
 */
package com.turvo.banking.branch.token.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.turvo.banking.AbstractCommonTest;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.customer.entities.Customer;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
public class TokenServiceTest extends AbstractCommonTest{
	
	@Autowired
	TokenService tokenService;
	
	@Test
	public void createToken() {
		Token token = new Token();
		token.setBranchId(1);
		token.setStatus(TokenStatus.CREATED);
		Customer customer = new Customer();
		customer.setCustomerId(2L);
		customer.setType(CustomerType.PREMIUM);
		token.setCustomer(customer);
		token.setBranchServices(Arrays.asList(new Long[] {2L}));
		Integer number = tokenService.createToken(token);
		Token token1 = tokenService.getTokenByNumber(number);
		assertEquals(number, token1.getNumber());
	}
	
	@Test
	public void updateToken() {
		Token token = tokenService.getTokenByNumber(10);
		token.setPriority(100);
		assertEquals(tokenService.updateToken(token), true);
	}

	@Test
	public void deleteToken() {
		assertEquals(tokenService.deleteToken(852L), true);
	}
	
}