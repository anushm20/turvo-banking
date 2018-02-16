/**
 * Repository interface for Sequences
 */
package com.turvo.banking.common.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turvo.banking.common.entities.Sequences;

/**
 * @author anushm
 *
 */
@Repository
public interface SequencesRepository extends CrudRepository<Sequences, String> {

}
