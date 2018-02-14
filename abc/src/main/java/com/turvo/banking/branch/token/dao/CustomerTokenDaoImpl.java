/**
 * DAO Implementation for Customer Token
 */
package com.turvo.banking.branch.token.dao;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.token.database.CustomerTokenDatabase;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Repository
public class CustomerTokenDaoImpl implements CustomerTokenDao {

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#getCustomerTokenById(java.lang.Long)
	 */
	@Override
	public CustomerToken getCustomerTokenByNumber(Long number) {
		return CustomerTokenDatabase.customerTokenMap.get(number);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#createCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public Long createCustomerToken(CustomerToken token) {
		Long number = CustomerTokenDatabase.customerTokenId;
		token.setNumber(number);
		CustomerTokenDatabase.customerTokenMap.put(number, token);
		CustomerTokenDatabase.customerTokenId++;
		return number;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#updateCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateCustomerToken(CustomerToken token) {
		CustomerTokenDatabase.customerTokenMap.put(token.getNumber(),token);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.database.CustomerTokenDao#deleteCustomerToken(java.lang.Long)
	 */
	@Override
	public void deleteCustomerToken(Long number) {
		CustomerTokenDatabase.customerTokenMap.remove(number);
	}

}
