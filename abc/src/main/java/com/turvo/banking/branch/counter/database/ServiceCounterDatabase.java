/**
 * Database for Service Counter
 */
package com.turvo.banking.branch.counter.database;

import java.util.HashMap;
import java.util.Map;

import com.turvo.banking.branch.counter.entities.ServiceCounter;

/**
 * @author anushm
 *
 */
public class ServiceCounterDatabase {
	
	public static Long serviceCounterId = 1L;
	public static Map<Long, ServiceCounter> serviceCounterMap = new HashMap<>();
}
