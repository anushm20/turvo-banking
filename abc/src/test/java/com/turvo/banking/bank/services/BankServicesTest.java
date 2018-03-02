/**
 * Test Class for Bank Service
 */
package com.turvo.banking.bank.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.turvo.banking.AbstractCommonTest;
import com.turvo.banking.bank.model.BankService;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

/**
 * @author anushm
 *
 */
public class BankServicesTest extends AbstractCommonTest {

	@Autowired
	BankServices bankServices;

	@Test
	public void createBankService() throws BankEntityNotFoundException {
		BankService bankService = new BankService();
		bankService.setServiceName("Withdrawal");
		Long id = bankServices.createBankService(bankService);
		BankService service1 = bankServices.getBankServiceById(id);
		assertEquals(service1.getServiceName(), "Withdrawal");
	}

	@Test
	public void updateBankService() throws BankEntityNotFoundException {
		BankService bankService = bankServices.getBankServiceById(1002L);
		bankService.setServiceName("Deposit");
		bankServices.updateBankService(bankService);
		BankService service1 = bankServices.getBankServiceById(202L);
		assertEquals(service1.getServiceName(), "Deposit");
	}

	@Test
	public void deleteBankService() {
		bankServices.deleteBankService(202L);
		BankService service1;
		try {
			service1 = bankServices.getBankServiceById(1L);
			assertNull(service1);
		} catch (BankEntityNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllBankServices() {
		List<BankService> bankServices = new ArrayList<>();
		for (BankService bankService : bankServices) {
			System.out.println("Service :" + bankService.getServiceName() + 
							"ID" + bankService.getServiceId());
		}
	}
}
