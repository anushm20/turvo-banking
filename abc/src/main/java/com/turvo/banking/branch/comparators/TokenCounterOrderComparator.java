/**
 * Comparator used to sort token counters based on the order
 * customer has to visit
 */
package com.turvo.banking.branch.comparators;

import java.util.Comparator;

import com.turvo.banking.branch.counter.entities.TokenCounterMapper;

/**
 * @author anushm
 *
 */
public class TokenCounterOrderComparator implements Comparator<TokenCounterMapper> {

	@Override
	public int compare(TokenCounterMapper o1, TokenCounterMapper o2) {
		return o1.getOrder()-o1.getOrder();
	}

}
