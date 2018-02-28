/**
 * Comparator to compare tokens based on Priority
 */
package com.turvo.banking.common;

import java.util.Comparator;

import com.turvo.banking.branch.model.Token;

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
