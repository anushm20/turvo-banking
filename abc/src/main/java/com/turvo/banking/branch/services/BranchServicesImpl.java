/**
 * Services Implementation for Branch services
 */
package com.turvo.banking.branch.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.model.BranchService;
import com.turvo.banking.branch.repositories.BranchServiceRepository;

/**
 * @author anushm
 *
 */
@Service
public class BranchServicesImpl implements BranchServices {
	
	@Autowired
	BranchServiceRepository branchServiceRepo;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#getAllServicesForBranch(java.lang.Long)
	 */
	@Override
	public List<BranchService> getAllServicesForBranch(Long branchId) {
		// TODO write a custom query here
		return new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#getBranchServiceById(java.lang.Long)
	 */
	@Override
	public BranchService getBranchServiceById(Long branchServiceId) {
		return branchServiceRepo.findOne(branchServiceId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#createBranchService(com.turvo.banking.branch.entities.BranchService)
	 */
	@Override
	public Long createBranchService(BranchService service) {
		branchServiceRepo.save(service);
		return service.getBranchServiceId();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#updateBranchService(com.turvo.banking.branch.entities.BranchService)
	 */
	@Override
	public boolean updateBranchService(BranchService service) {
		BranchService saved = branchServiceRepo.save(service);
		if(Objects.nonNull(saved))
			return true;
		else 
			return false;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.BranchServices#deleteBranchService(java.lang.Long)
	 */
	@Override
	public boolean deleteBranchService(Long branchServiceId) {
		branchServiceRepo.delete(branchServiceId);
		BranchService service = getBranchServiceById(branchServiceId);
		if(Objects.isNull(service))
			return true;
		else 
			return false;
	}

}
