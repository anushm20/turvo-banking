/**
 * DAO interface for Customer
 */
package com.turvo.banking.customer.dao;

import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public interface CustomerDao {
	
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
	public Long insertCustomer(Customer customer);
	
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
