/**
 * Helper class to notify listeners that a new 
 * customer token has been created
 */
package com.turvo.banking.branch.token.services;

import java.util.Observable;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class CustomerTokenHelper extends Observable {
	
	private CustomerToken token;
	
	public CustomerTokenHelper() {
		
	}
	
	public CustomerTokenHelper(CustomerToken token) {
		this.token = token;
		setChanged();
		notifyObservers(token);
	}

	public CustomerToken getToken() {
		return token;
	}

}
