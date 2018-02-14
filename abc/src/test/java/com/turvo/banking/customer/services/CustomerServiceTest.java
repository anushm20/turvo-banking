/**
 * Test Class for Customer Service
 */
package com.turvo.banking.customer.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.AbcApplication;
import com.turvo.banking.customer.entities.Customer;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class CustomerServiceTest {

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
}
