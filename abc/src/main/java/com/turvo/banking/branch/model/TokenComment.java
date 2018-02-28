/**
 * Class to store tokens of a customer
 */
package com.turvo.banking.branch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author anushm
 *
 */
@Entity
@Table(name="token_comment")
public class TokenComment {
	
	@Id
	@GeneratedValue(generator = "commentId")
	@GenericGenerator(
			name="commentId",
			strategy="com.turvo.banking.common.IdOverrideTableGenerator",
			parameters = {
					@Parameter(name="segment_value", value="TokenCommentImpl"),
					@Parameter(name="entity_name", 
					value="com.turvo.banking.branch.token.entities.TokenComment")
			}
			)
	@Column(name="token_comment_id")
	@ApiModelProperty(notes = "Primary Key for this entity")
	private Long tokenCommentId;
	
	@ManyToOne
	@JoinColumn(name="token_id")
	@ApiModelProperty(notes = "Customer Token Number")
	private Token token;
	
	@Column(name="counter_id")
	@ApiModelProperty(notes = "Counter which served this token")
	private Long counterId;
	
	@Column(name="operator_id")
	@ApiModelProperty(notes = "Operator who worked on this token")
	private Long operatorId;
	
	@Column(name="comment")
	@ApiModelProperty(notes = "Comment added by the operator for this token")
	private String comment;

	public Long getTokenCommentId() {
		return tokenCommentId;
	}

	public void setTokenCommentId(Long tokenCommentId) {
		this.tokenCommentId = tokenCommentId;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Long getCounterId() {
		return counterId;
	}

	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}