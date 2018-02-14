/**
 * Database class for Customer Token 
 */
package com.turvo.banking.branch.token.database;

import java.util.HashMap;
import java.util.Map;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class CustomerTokenDatabase {
	
	public static Long customerTokenId = 1L;
	public static Map<Long, CustomerToken> customerTokenMap = new HashMap<>();
}
