/**
 * Factory class for picking counter based on Token priority
 */
package com.turvo.banking.branch.counter.strategies;

import com.turvo.banking.branch.counter.entities.CounterStrategyType;
import com.turvo.banking.branch.exceptions.CounterStrategyNotFoundException;
import com.turvo.banking.common.ApplicationContextProvider;

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
	 * @throws CounterStrategyNotFoundException
	 */
	public static CounterStrategyPicker getStrategyPicker(String strategyType) 
			throws CounterStrategyNotFoundException {
		
		if(CounterStrategyType.MIXED.toString().equalsIgnoreCase(strategyType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					("mixedCounterStrategy",MixedCounterStrategy.class);
		} else if (CounterStrategyType.SEPARATE.toString().equalsIgnoreCase(strategyType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					("separateCounterStrategy",SeparateCounterStrategy.class);
		} else {
			throw new CounterStrategyNotFoundException("Counter Picking Strategy Not FOund");
		}
		
	}
}
