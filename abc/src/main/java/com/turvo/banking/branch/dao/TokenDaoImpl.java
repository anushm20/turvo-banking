/**
 * DAO implementation for Token
 */
package com.turvo.banking.branch.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.banking.branch.model.Token;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

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
	@Transactional(readOnly=true)
	public Token getTokenById(Long tokenId) throws BankEntityNotFoundException {
		Optional<Token> token = Optional.ofNullable(em.find(Token.class, tokenId));
		if(token.isPresent())
			return token.get();
		else
			throw new BankEntityNotFoundException("Token not found with given ID : "+tokenId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Token getTokenBasedOnPriority(List<Long> tokenIds) {
		Query query= em.createNamedQuery("Token.findTokenBasedOnPriority", Token.class);
		query.setParameter("tokenIds", tokenIds);
		List<Token> tokens = query.getResultList();
		if(Objects.nonNull(tokens) && tokens.size() > 0) {
			return tokens.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Token getTokenByNumber(Integer number) throws BankEntityNotFoundException {
		Query query = em.createNamedQuery("Token.findTokenByNumber",Token.class);
		query.setParameter("number", number);
		List<Token> tokens = query.getResultList();
		if(Objects.nonNull(tokens) && tokens.size() > 0) {
			return tokens.get(0);
		} else {
			throw new BankEntityNotFoundException("Token not found with given Number : "+number);
		}
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
		Token token = em.find(Token.class, tokenId);
		if(Objects.nonNull(token)) {
			em.remove(token);
			return true;
		} else {
			return false;
		}
	}

}
