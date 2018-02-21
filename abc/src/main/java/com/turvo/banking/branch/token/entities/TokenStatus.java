/**
 * Possible status for customer token
 */
package com.turvo.banking.branch.token.entities;

/**
 * @author anushm
 *
 */
public enum TokenStatus {
	CREATED,
	QUEUED,
	INPROGRESS,
	REVISIT,
	COMPLETED,
	CANCELLED;	
}
