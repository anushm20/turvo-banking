/**
 * Database class for customer Database
 */
package com.turvo.banking.customer.database;

import java.util.HashMap;
import java.util.Map;

import com.turvo.banking.customer.entities.Customer;

/**
 * @author anushm
 *
 */
public class CustomerDB {
	
	public static Long customerId = 1L;
	public static Map<Long,Customer> customerMap = new HashMap<>();

}
