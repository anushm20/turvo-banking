/**
 * Implementation for Services of a Customer token
 */
package com.turvo.banking.branch.token.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.services.ApplicationContextProvider;
import com.turvo.banking.branch.token.dao.CustomerTokenDao;
import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Service
public class CustomerTokenServiceImpl implements CustomerTokenService {
	
	@Autowired 
	CustomerTokenDao tokenDao;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService#getCustomerTokenById(java.lang.Long)
	 */
	@Override
	public CustomerToken getCustomerTokenByNumber(Long number) {
		return tokenDao.getCustomerTokenByNumber(number);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService#
	 * createCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public Long createCustomerToken(CustomerToken token) {
		Long id = tokenDao.createCustomerToken(token);
		CustomerTokenHelper helper = ApplicationContextProvider.getApplicationContext().
				getBean("customTokenHelper",CustomerTokenHelper.class);
		helper.setCustomerToken(token);
		return id; 
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService
	 * #updateCustomerToken(com.turvo.banking.branch.token.entities.CustomerToken)
	 */
	@Override
	public void updateCustomerToken(CustomerToken token) {
		tokenDao.updateCustomerToken(token);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.services.CustomerTokenService#deleteCustomerToken(java.lang.Long)
	 */
	@Override
	public void deleteCustomerToken(Long tokenId) {
		tokenDao.deleteCustomerToken(tokenId);
	}

}
