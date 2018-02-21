/**
 * Comparator class used to find counter with lowest tokensa
 */
package com.turvo.banking.branch.comparators;

import java.util.Comparator;

import com.turvo.banking.branch.counter.entities.Counter;

/**
 * @author anushm
 *
 */
public class CounterTokenSizeComparator implements Comparator<Counter> {

	@Override
	public int compare(Counter o1, Counter o2) {
		return o1.getQueuedTokens().size() - o2.getQueuedTokens().size();
	}

}
