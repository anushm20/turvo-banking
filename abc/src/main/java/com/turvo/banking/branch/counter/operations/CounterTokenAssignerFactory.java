/**
 * Factory class for picking counter based on Token priority
 */
package com.turvo.banking.branch.counter.operations;

import com.turvo.banking.branch.counter.entities.StrategyType;
import com.turvo.banking.common.ApplicationContextProvider;

/**
 * @author anushm
 *
 */
public class CounterTokenAssignerFactory {
	
	public static CounterStrategyPicker getTokenPicker(String tokenType) 
			throws CounterStrategyNotFoundException {
		
		if(StrategyType.MIXED.toString().equalsIgnoreCase(tokenType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					("mixedCounterStrategy",MixedCounterStrategy.class);
		} else if (StrategyType.SEPARATE.toString().equalsIgnoreCase(tokenType)) {
			return ApplicationContextProvider.getApplicationContext().getBean
					("separateCounterStrategy",SeparateCounterStrategy.class);
		} else {
			throw new CounterStrategyNotFoundException("Counter Picking Strategy Not FOund");
		}
		
	}
}
