/**
 * Comparator used for sorting tokens in a service counter 
 * priority queue
 */
package com.turvo.banking.branch.services;

import java.util.Comparator;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class CustomerTokenComparator implements Comparator<CustomerToken> {

	@Override
	public int compare(CustomerToken o1, CustomerToken o2) {
		return (int) (o1.getNumber() - o2.getNumber());
	}

}
