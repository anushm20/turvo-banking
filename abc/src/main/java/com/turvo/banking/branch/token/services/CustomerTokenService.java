/**
 * 
 */
package com.turvo.banking.branch.token.services;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public interface CustomerTokenService {
	
	CustomerToken getCustomerTokenById(Long number);
	void createCustomerToken(CustomerToken token);
	void updateCustomerToken(CustomerToken token);
	void deleteCustomerToken(Long tokenId);
	
}
