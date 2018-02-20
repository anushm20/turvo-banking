/**
 * Test Class for Bank Service
 */
package com.turvo.banking.bank.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.AbcApplication;
import com.turvo.banking.bank.entities.BankService;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class BankServicesTest {
	
	@Autowired
	BankServices bankServices;
	
	@Test
	public void createBankService() {
		BankService bankService = new BankService();
		bankService.setServiceName("Withdrawal");
		Long id = bankServices.createBankService(bankService);
		BankService service2 = new BankService();
		service2.setServiceName("Test Service");
		Long id1 = bankServices.createBankService(service2);
		BankService service1 = bankServices.getBankServiceById(id);
		assertEquals(service1.getServiceName(), "Withdrawal");
	}
	
	@Test
	public void updateBankService() {
		BankService bankService = bankServices.getBankServiceById(1L);
		bankService.setServiceName("Deposit");
		bankServices.updateBankService(bankService);
		BankService service1 = bankServices.getBankServiceById(1L);
		assertEquals(service1.getServiceName(), "Deposit");
	}

	@Test
	public void deleteBankService() {
		bankServices.deleteBankService(1L);
		BankService service1 = bankServices.getBankServiceById(1L);
		assertNull(service1);
	}
	
	@Test
	public void getAllBankServices() {
		List<BankService> bankServices = new ArrayList<>();
		for (BankService bankService : bankServices) {
			System.out.println("Service :"+ bankService.getServiceName() + "ID"+bankService.getServiceId());
		}
	}
}
