/**
 * Service for generating Customer Token
 */
package com.turvo.banking.branch.token.services;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface CustomerTokenService {
	
	/**
	 * Get customer token by Id
	 * @param number
	 * @return customer token object
	 */
	CustomerToken getCustomerTokenByNumber(Long number);
	
	/**
	 * Create a new Customer Token
	 * @param token
	 * @return id of new token
	 */
	Long createCustomerToken(CustomerToken token);
	
	/**
	 * Update a Customer Token
	 * @param token
	 */
	void updateCustomerToken(CustomerToken token);
	
	/**
	 * Delete a Customer token
	 * @param tokenId
	 */
	void deleteCustomerToken(Long tokenId);
	
}
