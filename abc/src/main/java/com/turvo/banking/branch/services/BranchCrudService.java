/**
 * 
 */
package com.turvo.banking.branch.services;

import com.turvo.banking.branch.entities.Branch;

/**
 * @author anushm
 *
 */
public interface BranchCrudService {
	
	Branch getBranchById(Integer branchId);
	
	Integer createBranch(Branch branch);
	
	boolean updateBranch(Branch branch);
	
	boolean deleteBranch(Integer branchId);
}
