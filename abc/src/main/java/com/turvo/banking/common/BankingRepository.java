/**
 * 
 */
package com.turvo.banking.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author anushm
 *
 */
@NoRepositoryBean
public interface BankingRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {

}
