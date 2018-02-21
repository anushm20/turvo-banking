/**
 * 
 */
package com.turvo.banking.branch.counter.operations;

import java.util.Comparator;

import com.turvo.banking.branch.counter.entities.TokenCounterQueue;

/**
 * @author anushm
 *
 */
public class TokenCounterOrderComparator implements Comparator<TokenCounterQueue> {

	@Override
	public int compare(TokenCounterQueue o1, TokenCounterQueue o2) {
		return o1.getOrder()-o1.getOrder();
	}

}
