/**
 * Implementation class for Branch Dao
 */
package com.turvo.banking.branch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.turvo.banking.branch.database.BranchServicesDB;
import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;

/**
 * @author anushm
 *
 */

@Repository
public class BranchDaoImpl implements BranchDao {

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.dao.BranchDao#getServiceCountersForService(java.lang.Long)
	 */
	@Override
	public List<Long> getServiceCountersForService(Long serviceId) {
		return BranchServicesDB.serviceToserviceCounterMap.get(serviceId);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.dao.BranchDao#createServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void createServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping) {
		BranchServicesDB.serviceToserviceCounterMap.put(mapping.getServiceId(),mapping.getServiceCounters());
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.dao.BranchDao#updateServiceToServiceCounterMapping(com.turvo.banking.branch.entities.ServiceToServiceCounterMapping)
	 */
	@Override
	public void updateServiceToServiceCounterMapping(ServiceToServiceCounterMapping mapping) {
		BranchServicesDB.serviceToserviceCounterMap.put(mapping.getServiceId(),mapping.getServiceCounters());
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.dao.BranchDao#deleteServiceToServiceCounterMapping(java.lang.Long)
	 */
	@Override
	public void deleteServiceToServiceCounterMapping(Long serviceId) {
		BranchServicesDB.serviceToserviceCounterMap.remove(serviceId);
	}
}
