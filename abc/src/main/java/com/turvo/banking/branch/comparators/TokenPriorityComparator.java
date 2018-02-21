/**
 * Comparator to compare tokens based on Priority
 */
package com.turvo.banking.branch.comparators;

import java.util.Comparator;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
public class TokenPriorityComparator implements Comparator<Token> {

	@Override
	public int compare(Token o1, Token o2) {
		return o1.getPriority()-o2.getPriority();
	}

}
