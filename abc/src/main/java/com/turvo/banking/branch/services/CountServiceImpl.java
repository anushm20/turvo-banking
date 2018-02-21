/**
 * Service Implementation for Count Entity
 */
package com.turvo.banking.branch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.branch.entities.Count;
import com.turvo.banking.branch.repositories.CountDao;

/**
 * @author anushm
 *
 */
@Service
public class CountServiceImpl implements CountService {
	
	@Autowired
	CountDao countDao;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.CountService#getCountForUpdate(java.lang.String)
	 */
	@Override
	public Integer getCountForUpdate(Integer branchId,String name,boolean updateCount) {
		return countDao.getCountForUpdate(branchId,name,updateCount);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.CountService#createCount(com.turvo.banking.branch.entities.Count)
	 */
	@Override
	public Integer createCount(Count count) {
		return countDao.createCount(count);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.CountService#updateCount(com.turvo.banking.branch.entities.Count)
	 */
	@Override
	public boolean updateCount(Count count) {
		return countDao.updateCount(count);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.services.CountService#deleteCountForName(java.lang.String)
	 */
	@Override
	public boolean deleteCountForName(String name) {
		return countDao.deleteCountForName(name);
	}

}
