/**
 * Repository class for customer
 */
package com.turvo.banking.customer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
