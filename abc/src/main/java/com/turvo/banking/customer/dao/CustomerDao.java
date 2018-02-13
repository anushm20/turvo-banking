/**
 * 
 */
package com.turvo.banking.customer.dao;

import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public interface CustomerDao {
	
	public Customer getCustomerById(Long customerId);
	
	public Long insertCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
	
	public void deleteCustomer(Long customerId);

}
