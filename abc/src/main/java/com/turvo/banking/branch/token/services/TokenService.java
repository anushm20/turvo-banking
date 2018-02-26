/**
 * Service for generating Token
 */
package com.turvo.banking.branch.token.services;

import java.util.List;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
public interface TokenService {
	
	/**
	 * Get token by token Id
	 * @param tokenId
	 * @return  token object
	 */
	Token getTokenById(Long tokenId);
	
	/**
	 * Method to get token by token number
	 * @param number
	 * @return token object
	 */
	Token getTokenByNumber(Integer number);

	/**
	 * Method to return prioritized token among the list
	 * @param tokenIds
	 * @return
	 */
	Token getTokenBasedOnPriority(List<Long> tokenIds);
	
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
