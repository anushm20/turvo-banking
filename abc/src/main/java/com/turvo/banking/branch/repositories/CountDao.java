/**
 * 
 */
package com.turvo.banking.branch.repositories;

import com.turvo.banking.branch.entities.Count;

/**
 * @author anushm
 *
 */
public interface CountDao {

	Integer getCountForUpdate(Integer branchId,String name,boolean updateCount);
	
	Integer createCount(Count count);
	
	boolean updateCount(Count count);
	
	boolean deleteCountForName(String name);
}
