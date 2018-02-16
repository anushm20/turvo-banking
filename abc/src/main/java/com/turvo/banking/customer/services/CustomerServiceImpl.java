/**
 * Service Implementation for Customer Operations
 */
package com.turvo.banking.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.common.services.SequencesServices;
import com.turvo.banking.customer.entities.Customer;
import com.turvo.banking.customer.repositories.CustomerRepository;

/**
 * @author anushm
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	SequencesServices sequenceService;

	@Override
	public Customer getCustomerById(Long customerId) {
		return customerRepo.findOne(customerId);
	}

	@Override
	public Long createCustomer(Customer customer) {
		customer.setCustomerId(sequenceService.getSequenceForEntity("customers"));
		customerRepo.save(customer);
		return customer.getCustomerId();
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepo.delete(customerId);
	}

}
