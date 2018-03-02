/**
 * Implementation class for Branch CRUD operations
 */
package com.turvo.banking.branch.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.model.Branch;
import com.turvo.banking.branch.repositories.BranchRepository;
import com.turvo.banking.exceptions.BankEntityNotFoundException;

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
	public Branch getBranchById(Integer branchId) throws BankEntityNotFoundException {
		Optional<Branch> branch = Optional.ofNullable
							(branchRepo.findOne(branchId));
		if(branch.isPresent())
			return branch.get();
		else 
			throw new BankEntityNotFoundException("Branch not found"
					+ "		 with given ID : "+ branchId);
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
		Branch branch = branchRepo.findOne(branchId);
		if(Objects.isNull(branch))
			return true;
		else
			return false;
	}

}
