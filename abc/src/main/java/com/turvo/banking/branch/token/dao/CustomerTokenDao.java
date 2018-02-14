/**
 * DAO Interface for Customer Token
 */
package com.turvo.banking.branch.token.dao;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface CustomerTokenDao {
	/**
	 * Get Customer token based on ID
	 * @param number
	 * @return customer token object
	 */
	CustomerToken getCustomerTokenByNumber(Long number);
	
	/**
	 * Create a new customer token
	 * @param token
	 * @return ID of new token
	 */
	Long createCustomerToken(CustomerToken token);
	
	/**
	 * Update a new customer token
	 * @param token
	 */
	void updateCustomerToken(CustomerToken token);
	
	/**
	 * Delete a new customer token
	 * @param tokenId
	 */
	void deleteCustomerToken(Long tokenId);
}
