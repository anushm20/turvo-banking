/**
 * Service for generating Token
 */
package com.turvo.banking.branch.services;

import java.util.List;

import com.turvo.banking.branch.model.Token;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

/**
 * @author anushm
 *
 */
public interface TokenService {
	
	/**
	 * Get token by token Id
	 * @param tokenId
	 * @return  token object
	 * @throws BankEntityNotFoundException 
	 */
	Token getTokenById(Long tokenId) throws BankEntityNotFoundException;
	
	/**
	 * Method to get token by token number
	 * @param number
	 * @return token object
	 * @throws BankEntityNotFoundException 
	 */
	Token getTokenByNumber(Integer number) throws BankEntityNotFoundException;

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
