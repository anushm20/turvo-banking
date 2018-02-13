/**
 * 
 */
package com.turvo.banking.customer.services;

import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public interface CustomerService {
	
	public Customer getCustomerById(Long customerId);
	
	public Long createCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
	
	public void deleteCustomer(Long customerId);

}
