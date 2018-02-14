/**
 * Service Implementation for Customer Operations
 */
package com.turvo.banking.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.customer.dao.CustomerDao;
import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;

	@Override
	public Customer getCustomerById(Long customerId) {
		return customerDao.getCustomerById(customerId);
	}

	@Override
	public Long createCustomer(Customer customer) {
		return customerDao.insertCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerDao.deleteCustomer(customerId);
	}

}
