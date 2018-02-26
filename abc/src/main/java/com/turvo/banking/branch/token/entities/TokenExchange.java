/**
 * Class which is used for exchanging token 
 * between queues.
 * This can be duplicate class for TokenCounterMapper
 * but just want to keep the token exchange simple
 * between counters
 */
package com.turvo.banking.branch.token.entities;

import java.io.Serializable;

/**
 * @author anushm
 *
 */
public class TokenExchange implements Serializable {

	/*
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tokenId;
	private Long counterId;
	
	public TokenExchange() {
		
	}
	
	public TokenExchange(Long tokenId,Long counterId) {
		this.tokenId = tokenId;
		this.counterId = counterId;
	}
	
	public Long getTokenId() {
		return tokenId;
	}
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}
	public Long getCounterId() {
		return counterId;
	}
	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
