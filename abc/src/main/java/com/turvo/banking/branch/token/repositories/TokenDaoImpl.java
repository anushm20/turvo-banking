/**
 * 
 */
package com.turvo.banking.branch.token.repositories;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
@Repository
public class TokenDaoImpl implements TokenDao {
	
	@PersistenceContext
	EntityManager em;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.repositories.TokenDao#getTokenById(java.lang.Long)
	 */
	@Override
	public Token getTokenById(Long tokenId) {
		return em.find(Token.class, tokenId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.repositories.TokenDao#createToken(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	@Transactional
	public int createToken(Token token) {
		em.persist(token);
		em.flush();
		return token.getNumber();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.repositories.TokenDao#updateToken(com.turvo.banking.branch.token.entities.Token)
	 */
	@Override
	@Transactional
	public boolean updateToken(Token token) {
		Token saved = em.merge(token);
		if(Objects.nonNull(saved))
			return true;
		else 
			return false;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.token.repositories.TokenDao#deleteToken(int)
	 */
	@Override
	@Transactional
	public boolean deleteToken(Long tokenId) {
		Token token = getTokenById(tokenId);
		if(Objects.nonNull(token)) {
			em.remove(token);
			return true;
		} else {
			return false;
		}
	}

}
