/**
 * Test Class for Branch Service
 */
package com.turvo.banking.branch.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.turvo.banking.AbstractCommonTest;
import com.turvo.banking.bank.model.BankService;
import com.turvo.banking.bank.services.BankServices;
import com.turvo.banking.branch.model.BranchService;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

/**
 * @author anushm
 *
 */
public class BranchServiceTest extends AbstractCommonTest{
	
	@Autowired
	BranchServices branchServices;
	
	@Autowired
	BankServices bankService;
	
	@Test
	public void createBranchService() throws BankEntityNotFoundException {
		BranchService brService = new BranchService();
		brService.setBranchId(1);
		brService.setMultiCounter(false);
		BankService service = bankService.getBankServiceById(202L);
		brService.setService(service);
		Long id = branchServices.createBranchService(brService);
		BranchService dbFetch = branchServices.getBranchServiceById(id);
		assertEquals(dbFetch.getBranchId(), 1);
	}
	
	@Test
	public void updateBranchService() throws BankEntityNotFoundException {
		BranchService brService = branchServices.getBranchServiceById(2L);
		brService.setMultiCounter(true);
		assertEquals(branchServices.updateBranchService(brService), true);
	}
	
	@Test
	public void deleteBranchService() {
		assertEquals(branchServices.deleteBranchService(2L), true);
	}
}
