/**
 * Implementation class for Branch CRUD operations
 */
package com.turvo.banking.branch.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.model.Branch;
import com.turvo.banking.branch.repositories.BranchRepository;

/**
 * @author anushm
 *
 */
@Service("branchCrudService")
public class BranchModelServiceImpl implements BranchModelService {
	
	@Autowired
	BranchRepository branchRepo;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchCrudService#getBranchById(java.lang.Integer)
	 */
	@Override
	public Branch getBranchById(Integer branchId) {
		return branchRepo.findOne(branchId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchCrudService#createBranch(com.turvo.banking.branch.entities.Branch)
	 */
	@Override
	public Integer createBranch(Branch branch) {
		branchRepo.save(branch);
		return branch.getBranchId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchCrudService#updateBranch(com.turvo.banking.branch.entities.Branch)
	 */
	@Override
	public boolean updateBranch(Branch branch) {
		Branch saved = branchRepo.save(branch);
		if(Objects.nonNull(saved))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchCrudService#deleteBranch(com.turvo.banking.branch.entities.Branch)
	 */
	@Override
	public boolean deleteBranch(Integer branchId) {
		branchRepo.delete(branchId);
		Branch branch = getBranchById(branchId);
		if(Objects.isNull(branch))
			return true;
		else
			return false;
	}

}
