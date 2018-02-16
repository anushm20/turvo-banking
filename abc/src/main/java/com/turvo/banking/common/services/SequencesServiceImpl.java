/**
 * Service Implementation for Sequences
 */
package com.turvo.banking.common.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.banking.common.entities.Sequences;
import com.turvo.banking.common.repositories.SequencesRepository;

/**
 * @author anushm
 *
 */
@Service
public class SequencesServiceImpl implements SequencesServices {
	
	@Autowired
	SequencesRepository sequenceRepo;

	/* (non-Javadoc)
	 * @see com.turvo.banking.common.services.SequencesServices#getSequenceForEntity(java.lang.String)
	 */
	@Override
	public Long getSequenceForEntity(String entityName) {
		Sequences sequence = sequenceRepo.findOne(entityName);
		if(Objects.isNull(sequence)) {
			sequence = new Sequences();
			sequence.setEntityName(entityName);
			sequence.setSequence(1L);
		} 
		Long seq = sequence.getSequence().longValue();
		sequence.setSequence(seq+1L);
		insertOrUpdateSequenceForEntity(sequence);
		return seq;
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.common.services.SequencesServices#
	 * insertOrUpdateSequenceForEntity(com.turvo.banking.common.entities.Sequences)
	 */
	@Override
	public void insertOrUpdateSequenceForEntity(Sequences sequence) {
		sequenceRepo.save(sequence);
	}

	/* (non-Javadoc)
	 * @see com.turvo.banking.common.services.SequencesServices#deleteSequenceForEntity(java.lang.String)
	 */
	@Override
	public void deleteSequenceForEntity(String entityName) {
		sequenceRepo.delete(entityName);
	}

}
