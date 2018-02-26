/**
 * Factory class for picking counter based on Token priority
 */
package com.turvo.banking.branch.counter.strategies;

import com.turvo.banking.branch.counter.entities.CounterStrategyType;
import com.turvo.banking.branch.exceptions.BankEntityNotFoundException;
import com.turvo.banking.common.ApplicationContextProvider;
import com.turvo.banking.common.BankingConstants;

/**
 * @author anushm
 *
 */
public class CounterTokenAssignerFactory {
	
	/**
	 * Method to return proper instance of strategy picker based on the
	 * branch strategy
	 * @param strategyType
	 * @return counter strategy pickers
	 * @throws BankEntityNotFoundException
	 */
	public static CounterStrategyPicker getStrategyPicker(String strategyType) 
			throws BankEntityNotFoundException {
		
		if(CounterStrategyType.MIXED.toString().equalsIgnoreCase(strategyType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					(BankingConstants.MIXED_STRATEGY,MixedCounterStrategy.class);
		} else if (CounterStrategyType.SEPARATE.toString().equalsIgnoreCase(strategyType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					(BankingConstants.SEPARATE_STRATEGY,SeparateCounterStrategy.class);
		} else {
			throw new BankEntityNotFoundException("Counter Picking Strategy Not Found");
		}
		
	}
}
