/**
 * 
 */
package com.turvo.banking.branch.token.dao;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface CustomerTokenDao {

	CustomerToken getCustomerTokenById(Long number);
	void createCustomerToken(CustomerToken token);
	void updateCustomerToken(CustomerToken token);
	void deleteCustomerToken(Long tokenId);
}
