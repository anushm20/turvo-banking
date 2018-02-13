/**
 * 
 */
package com.turvo.banking.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.entities.BankService;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ComponentScan("com.turvo.banking")
public class BankServicesTest {
	
	@Autowired
	BankServices bankServices;
	
	@Test
	public void createBankService() {
		BankService service = new BankService();
		service.setServiceName("Withdrawal");
		Long id = bankServices.createBankService(service);
		BankService service1 = bankServices.getBankServiceById(id);
		assertEquals(service1.getServiceName(), "Withdrawal");
	}

}
