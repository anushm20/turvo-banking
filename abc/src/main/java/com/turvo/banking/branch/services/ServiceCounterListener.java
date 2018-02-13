/**
 * 
 */
package com.turvo.banking.branch.services;

import java.util.Observable;
import java.util.Observer;

import com.turvo.banking.branch.token.entities.CustomerToken;

/**
 * @author anushm
 *
 */
public class ServiceCounterListener implements Observer {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	public void updateTokeninQueues(CustomerToken token) {
		// Should consider Premium customer / Regular Customer logic as well
	}
	
}
