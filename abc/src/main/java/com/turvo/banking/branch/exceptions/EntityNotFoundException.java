/**
 * Exception class when a particular strategy is not found
 */
package com.turvo.banking.branch.exceptions;

/**
 * @author anushm
 *
 */
public class EntityNotFoundException extends Exception{
	
	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String s) {
		super(s);
	}

}