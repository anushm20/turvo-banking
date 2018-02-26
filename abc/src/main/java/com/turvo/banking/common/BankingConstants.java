/**
 * Contains constants required for the application
 */
package com.turvo.banking.common;

/**
 * @author anushm
 *
 */
public final class BankingConstants {
	
	public static final String MIXED_STRATEGY = "mixedCounterStrategy";
	public static final String SEPARATE_STRATEGY = "separateCounterStrategy";
	public static final String TOKEN_NUMBER = "TOKEN_NUMBER";
	
	// Rabbit MQ queues configuration
	public static final String CREATED_TOKEN_QUEUE= "tokens-queue";
    public static final String CREATED_TOKEN_EXCHANGE = "tokens-exchange";
    public static final String TOKEN_COUNTER_EXCHANGE_QUEUE= "tokens-queue";
    public static final String TOKEN_COUNTER_EXCHANGE = "tokens-exchange";

}
