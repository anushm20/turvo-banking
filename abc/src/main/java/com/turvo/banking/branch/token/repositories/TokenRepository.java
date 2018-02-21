/**
 * 
 */
package com.turvo.banking.branch.token.repositories;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.token.entities.Token;
import com.turvo.banking.common.BankingRepository;

/**
 * @author anushm
 *
 */
@Repository	
public interface TokenRepository extends BankingRepository<Token, Long> {
	
}
