/**
 * Test Class for Service Counter Service
 */
package com.turvo.banking.branch.counter.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.turvo.banking.AbstractCommonTest;
import com.turvo.banking.branch.model.Counter;
import com.turvo.banking.branch.model.CounterType;
import com.turvo.banking.branch.model.Token;
import com.turvo.banking.branch.services.CounterService;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

/**
 * @author anushm
 *
 */
public class CounterServiceTest extends AbstractCommonTest{
	
	@Autowired
	CounterService counterService;
	
	@Test
	public void createCounter() throws BankEntityNotFoundException {
		Counter counter = new Counter();
		counter.setCounterType(CounterType.PREMIUM);
		counter.setBrServiceId(2L);
		Long id = counterService.createCounter(counter);
		Counter counter1 = counterService.getCounterById(id);
		assertEquals(counter1.getBrServiceId().longValue(), 2L);
	}
	
	@Test
	public void updateCounter() throws BankEntityNotFoundException {
		Counter counter = counterService.getCounterById(52L);
		counter.setOrder(100);
		boolean success = counterService.updateCounter(counter);
		assertEquals(true, success);
	}
	
	@Test
	public void deleteCounter() {
		boolean success = counterService.deleteCounter(52L);
		assertEquals(true, success);
	}
	
	@Test
	public void getCountersByServiceAndType() {
		List<Counter> counters = counterService.getCountersByServiceAndType
							(2L, CounterType.PREMIUM);
		counters.forEach((counter)->{
			assertEquals(CounterType.PREMIUM.toString(),counter.
									getCounterType().toString());
		});
	}
	
	@Test
	public void getCounterWithMinTokens() {
		Counter counter = counterService.getCounterWithMinTokens
								(Arrays.asList(new Long[] {52L,102L}));
		assertEquals(counter.getCounterId().longValue(), 102L);
	}
	
	@Test
	public void getNextToken() {
		Token token = counterService.getNextTokensForCounter(252L);
		assertNull(token);
	}
	
	@Test
	public void getTokenNumbersAtCounter() {
		List<Integer> tokenNumbers = counterService.getTokenNumbersAtCounter(52L);
		assertEquals(0, tokenNumbers.size());
	}
}
