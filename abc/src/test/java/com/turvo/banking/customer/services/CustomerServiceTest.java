/**
 * Test Class for Customer Service
 */
package com.turvo.banking.customer.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.turvo.banking.AbstractCommonTest;
import com.turvo.banking.customer.entities.Customer;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
public class CustomerServiceTest  extends AbstractCommonTest{

	@Autowired
	CustomerService customerServcie;
	
	@Test
	public void createCustomer() {
		Customer customer = new Customer();
		customer.setName("MAK");
		customer.setPhoneNumber(9000788199L);
		customer.setType(CustomerType.REGULAR);
		Long id = customerServcie.createCustomer(customer);
		Customer cust = customerServcie.getCustomerById(id);
		assertEquals(cust.getName(), "MAK");
	}
	
	@Test
	public void updateCustomer() {
		Customer customer = customerServcie.getCustomerById(1L);
		customer.setName("Updated");
		assertEquals(customerServcie.updateCustomer(customer), true);
	}
	
	@Test
	public void deleteCustomer() {
		assertEquals(customerServcie.deleteCustomer(1L), true);
	}
}
