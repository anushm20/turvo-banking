/**
 * DAO implemnetation for Customer 
 */
package com.turvo.banking.customer.dao;

import org.springframework.stereotype.Repository;

import com.turvo.banking.customer.database.CustomerDB;
import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#getCustomerById(java.lang.Long)
	 */
	@Override
	public Customer getCustomerById(Long customerId) {
		return CustomerDB.customerMap.get(customerId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#insertCustomer(com.turvo.banking.customer.entities.Customer)
	 */
	@Override
	public Long insertCustomer(Customer customer) {
		Long id = CustomerDB.customerId;
		customer.setCustomerId(id);
		CustomerDB.customerMap.put(id, customer);
		CustomerDB.customerId++;
		return id;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#updateCustomer(com.turvo.banking.customer.entities.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) {
		CustomerDB.customerMap.put(customer.getCustomerId(), customer);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#deleteCustomer(java.lang.Long)
	 */
	@Override
	public void deleteCustomer(Long customerId) {
		CustomerDB.customerMap.remove(customerId);
	}

}
