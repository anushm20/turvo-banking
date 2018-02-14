/**
 * Service for Customer Operations
 */
package com.turvo.banking.customer.services;

import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public interface CustomerService {
	
	/**
	 * Get a customer based on Id
	 * @param customerId
	 * @return Customer Object
	 */
	public Customer getCustomerById(Long customerId);
	
	/**
	 * Create a new customer
	 * @param customer
	 * @return id of new customer
	 */
	public Long createCustomer(Customer customer);
	
	/**
	 * Update a new customer
	 * @param customer
	 */
	public void updateCustomer(Customer customer);
	
	/**
	 * Delete a new customer
	 * @param customerId
	 */
	public void deleteCustomer(Long customerId);

}
