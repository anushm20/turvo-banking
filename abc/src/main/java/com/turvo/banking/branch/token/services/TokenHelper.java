/**
 * Helper class to notify listeners that a new 
 * customer token has been created
 */
package com.turvo.banking.branch.token.services;

import java.util.Observable;

import com.turvo.banking.branch.token.entities.Token;

/**
 * @author anushm
 *
 */
public class TokenHelper extends Observable {
	
	public void notifyPicker(Token token) {
		setChanged();
		notifyObservers(token);
	}
	
}