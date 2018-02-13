/**
 * 
 */
package com.turvo.banking.branch.token.dao;

import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.customer.dao.CustomerDao;
import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public class CustomerTokenDaoImpl implements CustomerTokenDao, CustomerDao {

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#getCustomerById(java.lang.Long)
	 */
	@Override
	public Customer getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#insertCustomer(com.turvo.banking.customer.entities.Customer)
	 */
	@Override
	public Long insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#updateCustomer(com.turvo.banking.customer.entities.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.customer.dao.CustomerDao#deleteCustomer(java.lang.Long)
	 */
	@Override
	public void deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#getCustomerTokenById(java.lang.Long)
	 */
	@Override
	public CustomerToken getCustomerTokenById(Long number) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#createCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void createCustomerToken(CustomerToken token) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#updateCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateCustomerToken(CustomerToken token) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#deleteCustomerToken(java.lang.Long)
	 */
	@Override
	public void deleteCustomerToken(Long tokenId) {
		// TODO Auto-generated method stub

	}

}
