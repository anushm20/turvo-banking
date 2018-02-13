/**
 * 
 */
package com.turvo.banking.branch.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.turvo.banking.branch.entities.ServiceCounter;

/**
 * @author anushm
 *
 */
public class BranchServicesDB {
	
	static Map<Long, List<Long>> serviceToserviceCounterMap = new HashMap<>();
	
	static Map<Long, ServiceCounter> serviceCounterMap = new HashMap<>();
	
}
