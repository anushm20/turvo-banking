/**
 * Test Class for Service Counter Service
 */
package com.turvo.banking.branch.counter.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.AbcApplication;
import com.turvo.banking.branch.counter.entities.BranchCounter;
import com.turvo.banking.branch.counter.entities.CounterType;
import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.entities.TokenStatus;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class CounterServiceTest {
	
	@Autowired
	BranchCounterService counterService;
	
	@Test
	public void createServiceCounter() {
		BranchCounter counter = new BranchCounter();
		counter.setCounterType(CounterType.PREMIUM);
		counter.setServiceId(1L);
		PriorityQueue<CustomerToken> queue = new PriorityQueue<>();
		counter.setTokenQueue(queue);
		Long id = counterService.createServiceCounter(counter);
		BranchCounter counter1 = counterService.getServiceCounterById(id);
		assertEquals(counter1.getServiceId().longValue(), 1L);
		// Update queue
		CustomerToken token = new CustomerToken();
		token.setStatus(TokenStatus.CREATED);
		token.setNumber(1L);
		token.setCustomerType(CustomerType.PREMIUM.toString());
		token.setServices(Arrays.asList(new Long[] {1L,2L,3L,4L}));
		counter1.getTokenQueue().add(token);
		// Assert token
		BranchCounter counter2 = counterService.getServiceCounterById(id);
		assertEquals(counter2.getTokenQueue().peek(), token);
	}

}
