/**
 * Service Interface for Count Entity 
 */
package com.turvo.banking.branch.services;

import com.turvo.banking.branch.model.Count;

/**
 * @author anushm
 *
 */
public interface CountService {
	
	/**
	 * Get the count for the key and update the value in database
	 * if needed
	 * @param branchId
	 * @param name
	 * @param updateCount
	 * @return count for the key
	 */
	Integer getCountForUpdate(Integer branchId,String name,boolean updateCount);
	
	/**
	 * Create a Count for new key
	 * @param count
	 * @return value for the key
	 */
	Integer createCount(Count count);
	
	/**
	 * Update Count for the key
	 * @param count
	 * @return success or failure
	 */
	boolean updateCount(Count count);
	
	/** 
	 * Delete count for the given key
	 * @param name
	 * @return success or failure
	 */
	boolean deleteCountForName(String name);
	
}
