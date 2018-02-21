/**
 * Implementation for the DAO interface of Count entity
 */
package com.turvo.banking.branch.repositories;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;

import com.turvo.banking.branch.entities.Count;

/**
 * @author anushm
 *
 */
@Repository
public class CountDaoImpl implements CountDao {
	
	@PersistenceContext
	EntityManager em;

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.repositories.CountDao#getCountForUpdate(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@org.springframework.transaction.annotation.Transactional(isolation=Isolation.SERIALIZABLE)
	public Integer getCountForUpdate(Integer branchId,String name,boolean updateCount) {
		Query query = em.createNamedQuery("Count.findByBranchAndName",Count.class);
		query.setParameter("branchId", branchId);
		query.setParameter("name", name);
		List<Count> results = query.getResultList();
		if(Objects.nonNull(results) && results.size() > 0) {
			Count count =results.get(0);
			// Now call for update
			Integer value = count.getNumber()+1;
			if(updateCount) {
				count.setNumber(value);
				updateCount(count);
				em.flush();
			}
			return value;
		} else {
			Count count = new Count();
			count.setName(name);
			count.setBranchId(branchId);
			count.setNumber(1);
			return createCount(count);
		}
	}
	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.repositories.CountDao#createCount(com.turvo.banking.branch.entities.Count)
	 */
	@Override
	@Transactional
	public Integer createCount(Count count) {
		em.persist(count);
		em.flush();
		return count.getNumber();
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.repositories.CountDao#updateCount(com.turvo.banking.branch.entities.Count)
	 */
	@Override
	@Transactional
	public boolean updateCount(Count count) {
		Count saved = em.merge(count);
		if(Objects.nonNull(saved))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.branch.repositories.CountDao#deleteCountForName(java.lang.String)
	 */
	@Override
	@Transactional
	public boolean deleteCountForName(String name) {
		Count count = em.find(Count.class, name);
		if(Objects.nonNull(count)) {
			em.remove(count);
			return true;
		} else {
			return false;
		}
	}

}
