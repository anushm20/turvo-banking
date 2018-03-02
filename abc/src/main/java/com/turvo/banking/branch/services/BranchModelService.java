/**
 * Interface for Branch CRUD operations
 */
package com.turvo.banking.branch.services;

import com.turvo.banking.branch.model.Branch;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

/**
 * @author anushm
 *
 */
public interface BranchModelService {
	
	/**
	 * Get Branch by ID	
	 * @param branchId
	 * @return Branch
	 * @throws BankEntityNotFoundException 
	 */
	Branch getBranchById(Integer branchId) throws BankEntityNotFoundException;
	
	/**
	 * Create a new branch
	 * @param branch
	 * @return ID of the new Branch
	 */
	Integer createBranch(Branch branch);
	
	/**
	 * Update an existing branch
	 * @param branch
	 * @return success or failure
	 */
	boolean updateBranch(Branch branch);
	
	/**
	 * Delete an existing Branch
	 * @param branchId
	 * @return success or failure
	 */
	boolean deleteBranch(Integer branchId);
}
