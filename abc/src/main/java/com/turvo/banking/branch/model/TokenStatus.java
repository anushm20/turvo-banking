/**
 * Possible status for token
 */
package com.turvo.banking.branch.model;

/**
 * @author anushm
 *
 */
public enum TokenStatus {
	// Status for token
	CREATED,
	QUEUED,
	INPROGRESS,
	COMPLETED,
	CANCELLED,
	// Status which can be used at each counter level
	COUNTER_REVISIT,
	COUNTER_COMPLETE;
}
