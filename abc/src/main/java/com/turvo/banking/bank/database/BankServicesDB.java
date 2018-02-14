/**
 * Database class for Bank Service
 */
package com.turvo.banking.bank.database;

import java.util.HashMap;
import java.util.Map;

import com.turvo.banking.bank.entities.BankService;

/**
 * @author anushm
 *
 */
public class BankServicesDB {
	
	public static Long bankServicesCount = 1L;
	
	public static Map<Long, BankService> bankServices = new HashMap<>();

}
