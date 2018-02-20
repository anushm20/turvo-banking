/**
 * Services interface for Branch Service
 */
package com.turvo.banking.branch.services;

import java.util.List;

import com.turvo.banking.branch.entities.BranchService;

/**
 * @author anushm
 *
 */
public interface BranchServices {
	
	/**
	 * Method to get list of services available in a branch
	 * @param branchId
	 * @return list of branch services available
	 */
	List<BranchService> getAllServicesForBranch(Long branchId);
	
	/**
	 * @param branchServiceId
	 * Method get branch service by id
	 * @return branch service id
	 */
	BranchService getBranchServiceById(Long branchServiceId);
	
	/**
	 * Method to create a new mapping of a service to a bank branch
	 * @param service
	 * @return ID of the new service created
	 */
	Long createBranchService(BranchService service);
	
	/**
	 * Method to update mapping of a service to a bank branch
	 * @param service
	 * @return success or failure
	 */
	boolean updateBranchService(BranchService service);
	
	/**
	 * Method to delete mapping of a service to a bank branch
	 * @param branchServiceId
	 * @return
	 */
	boolean deleteBranchService(Long branchServiceId);

}
