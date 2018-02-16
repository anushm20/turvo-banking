/**
 * Interface for Sequences
 */
package com.turvo.banking.common.services;

import com.turvo.banking.common.entities.Sequences;

/**
 * @author anushm
 *
 */
public interface SequencesServices {

	/**
	 * Method get sequence for an entity
	 * and increments it by one 
	 * @param entityName
	 * @return last value used for the entity
	 */
	Long getSequenceForEntity(String entityName);
	
	/**
	 * Inserts or update sequence for an entity
	 * @param entityName
	 */
	void insertOrUpdateSequenceForEntity(Sequences sequence);
	
	/**
	 * Deletes sequence for an entity
	 * @param entityName
	 */
	void deleteSequenceForEntity(String entityName);
	

}
