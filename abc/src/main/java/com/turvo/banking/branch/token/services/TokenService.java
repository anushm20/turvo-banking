/**
 * Service for generating Token
 */
package com.turvo.banking.branch.token.services;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
public interface TokenService {
	
	/**
	 * Get token by token Number
	 * @param number
	 * @return  token object
	 */
	Token getTokenByNumber(int number);
	
	/**
	 * Create a new  Token
	 * @param token
	 * @return id of new token
	 */
	int createToken(Token token);
	
	/**
	 * Update a  Token
	 * @param token
	 */
	void updateToken(Token token);
	
	/**
	 * Delete a  token
	 * @param tokenId
	 */
	void deleteToken(int number);
	
}
