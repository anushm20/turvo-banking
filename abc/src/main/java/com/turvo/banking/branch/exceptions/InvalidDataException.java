/**
 * Exception class when a particular strategy is not found
 */
package com.turvo.banking.branch.exceptions;

/**
 * @author anushm
 *
 */
public class InvalidDataException extends Exception{
	
	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException(String s) {
		super(s);
	}

}
