/**
 * Generic repository interface 
 */
package com.turvo.banking.common;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author anushm
 *
 */
@NoRepositoryBean
public interface BankingRepository<T,ID extends Serializable> extends CrudRepository<T, ID> {

}
