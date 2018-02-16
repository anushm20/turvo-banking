/**
 * 
 */
package com.turvo.banking.branch.token.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
@Repository	
public interface CustomerTokenRepository extends CrudRepository<CustomerToken, Long> {

}
