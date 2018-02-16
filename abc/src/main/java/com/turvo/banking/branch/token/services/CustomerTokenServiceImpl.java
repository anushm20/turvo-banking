/**
 * Implementation for Services of a Customer token
 */
package com.turvo.banking.branch.token.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.token.entities.CustomerToken;
import com.turvo.banking.branch.token.repositories.CustomerTokenRepository;
import com.turvo.banking.common.ApplicationContextProvider;
import com.turvo.banking.common.services.SequencesServices;

/**
 * @author anushm
 *
 */
@Service
public class CustomerTokenServiceImpl implements CustomerTokenService {
	
	@Autowired 
	CustomerTokenRepository tokenRepo;
	
	@Autowired
	SequencesServices sequenceService;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService#getCustomerTokenById(java.lang.Long)
	 */
	@Override
	public CustomerToken getCustomerTokenByNumber(Long number) {
		return tokenRepo.findOne(number);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService#
	 * createCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public Long createCustomerToken(CustomerToken token) {
		token.setNumber(sequenceService.getSequenceForEntity("tokens"));
		tokenRepo.save(token);
		CustomerTokenHelper helper = ApplicationContextProvider.getApplicationContext().
				getBean("customTokenHelper",CustomerTokenHelper.class);
		helper.setCustomerToken(token);
		return token.getNumber(); 
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService
	 * #updateCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateCustomerToken(CustomerToken token) {
		tokenRepo.save(token);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService#deleteCustomerToken(java.lang.Long)
	 */
	@Override
	public void deleteCustomerToken(Long tokenId) {
		tokenRepo.delete(tokenId);
	}

}
