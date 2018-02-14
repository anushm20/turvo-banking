/**
 * 
 */
package com.turvo.banking.branch.token.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.AbcApplication;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class TokenServiceTest {
	
	@Autowired
	CustomerTokenService tokenService;
	
	@Test
	public void createCustomerToken() {
		CustomerToken token = new CustomerToken();
		token.setStatus(TokenStatus.CREATED);
		token.setCustomerType(CustomerType.PREMIUM.toString());
		token.setCustomerId(2L);
		token.setServices(Arrays.asList(new Long[] {1L,2L,3L,4L}));
		Long id = tokenService.createCustomerToken(token);
		CustomerToken token1 = tokenService.getCustomerTokenByNumber(id);
		assertEquals(token1.getCustomerId().longValue(), 2L);
	}

}
