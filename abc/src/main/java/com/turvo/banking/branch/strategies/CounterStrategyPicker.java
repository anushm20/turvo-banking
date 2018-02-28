/**
 * Interface for counter stratgies in a branch
 * and update the token into respective queues
 */
package com.turvo.banking.branch.strategies;

import com.turvo.banking.branch.model.Token;
import com.turvo.banking.exceptions.BankEntityNotFoundException;
import com.turvo.banking.exceptions.InvalidDataException;

/**
 * @author anushm
 *
 */
public interface CounterStrategyPicker {
	
	/**
	 * Update the first counter queue based on the services
	 * 	 selected and customer type
	 * @param token
	 * @throws InvalidDataException 
	 * @throws BankEntityNotFoundException 
	 * @return success or failure
	 */
	public boolean queueTokenAtFirstCounter(Token token) 
				throws InvalidDataException, BankEntityNotFoundException;
	
	/**
	 * Process the token after completing the action at each 
	 * counter
	 * @param token
	 * @param last counter id which processed the token 
	 * @return success or failure
	 * @throws BankEntityNotFoundException 
	 */
	public boolean processTokenToNextStages(Token token,Long counterId)
						throws BankEntityNotFoundException;

}
