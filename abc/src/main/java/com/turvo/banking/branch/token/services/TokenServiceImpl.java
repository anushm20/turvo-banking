/**
 * Implementation for Services of a  token
 */
package com.turvo.banking.branch.token.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.repositories.CountDao;
import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.repositories.TokenDao;

/**
 * @author anushm
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	TokenDao tokenDao;
	
	@Autowired
	CountDao countDao;
	
	@Autowired
	TokenHelper helper;
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService#getTokenById(java.lang.Long)
	 */
	@Override
	public Token getTokenById(Long tokenId) {
		return tokenDao.getTokenById(tokenId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService#
	 * createToken(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public int createToken(Token token) {
		token.setNumber(countDao.getCountForUpdate
					(token.getBranchId(), "TOKEN_NUMBER",true));
		Integer number = tokenDao.createToken(token);
		helper.sendToken(token.getTokenId());
		return number; 
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService
	 * #updateToken(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public boolean updateToken(Token token) {
		return tokenDao.updateToken(token);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService#deleteToken(java.lang.Long)
	 */
	@Override
	public boolean deleteToken(Long tokenId) {
		return tokenDao.deleteToken(tokenId);
	}

}
