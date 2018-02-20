/**
 * Implementation for Services of a  token
 */
package com.turvo.banking.branch.token.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.branch.token.repositories.TokenRepository;

/**
 * @author anushm
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired 
	TokenRepository tokenRepo;
	
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService#getTokenById(java.lang.Long)
	 */
	@Override
	public Token getTokenByNumber(int number) {
		return tokenRepo.findOne(number);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService#
	 * createToken(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public int createToken(Token token) {
		tokenRepo.save(token);
		/*TokenHelper helper = ApplicationContextProvider.getApplicationContext().
				getBean("customTokenHelper",TokenHelper.class);
		helper.notifyPicker(token);*/
		return token.getNumber(); 
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService
	 * #updateToken(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	public void updateToken(Token token) {
		tokenRepo.save(token);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.TokenService#deleteToken(java.lang.Long)
	 */
	@Override
	public void deleteToken(int number) {
		tokenRepo.delete(number);
	}

}
