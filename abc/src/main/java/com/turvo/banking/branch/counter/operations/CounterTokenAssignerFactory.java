/**
 * Factory class for picking counter based on Token priority
 */
package com.turvo.banking.branch.counter.operations;

import com.turvo.banking.common.ApplicationContextProvider;
import com.turvo.banking.customer.entities.CustomerType;

/**
 * @author anushm
 *
 */
public class CounterTokenAssignerFactory {
	
	public static BranchCounterTokenPicker getTokenPicker(String tokenType) 
			throws CounterStrategyNotFoundException {
		
		if(CustomerType.PREMIUM.toString().equalsIgnoreCase(tokenType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					("premiumServiceCounter",PremiumCounterTokenPicker.class);
		} else if (CustomerType.REGULAR.toString().equalsIgnoreCase(tokenType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					("regularServiceCounter",PremiumCounterTokenPicker.class);
		} else {
			throw new CounterStrategyNotFoundException("Counter Picking Strategy Not FOund");
		}
		
	}
}
