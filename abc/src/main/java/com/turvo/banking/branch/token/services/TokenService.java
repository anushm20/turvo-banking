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
	 * @param tokenId
	 * @return  token object
	 */
	Token getTokenById(Long tokenId);
	
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
	boolean updateToken(Token token);
	
	/**
	 * Delete a  token
	 * @param tokenId
	 */
	boolean deleteToken(Long tokenId);
	
}
