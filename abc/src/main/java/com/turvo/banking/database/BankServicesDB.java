/**
 * 
 */
package com.turvo.banking.database;

import java.util.HashMap;
import java.util.Map;

import com.turvo.banking.entities.BankService;

/**
 * @author anushm
 *
 */
public class BankServicesDB {
	
	public static Long bankServicesCount = 1L;
	
	public static Map<Long, BankService> bankServices = new HashMap<>();

}
