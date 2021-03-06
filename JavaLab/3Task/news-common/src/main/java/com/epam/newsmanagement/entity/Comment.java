/**
 * 
 */
package com.epam.newsmanagement.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Uladzislau_Kaminski
 *
 */
@Entity
@Table(name = "COMMENTS")
@SequenceGenerator(name = "COMMENTS_COMMENT_ID_SEQ", sequenceName = "COMMENTS_COMMENT_ID_SEQ", allocationSize = 1)
public class Comment {
	
	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_COMMENT_ID_SEQ")
	private Long commentId;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "NEWS_ID", nullable = false)
	private News news;
	
	@NotNull
	@Size(min=5, max =100)
	@Column(name="COMMENT_TEXT")
	private String text;
	
	@Column(name="CREATION_DATE")
	private Timestamp creationDate;

	public Comment() {
	}

	/**
	 * @return the commentId
	 */
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the news
	 */
	public News getNews() {
		return news;
	}

	/**
	 * @param news the news to set
	 */
	public void setNews(News news) {
		this.news = news;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}



	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (getCommentId() == null) {
			if (other.getCommentId() != null)
				return false;
		} else if (!getCommentId().equals(other.getCommentId()))
			return false;
		if (getCreationDate() == null) {
			if (other.getCreationDate() != null)
				return false;
		} else if (!getCreationDate().equals(other.getCreationDate()))
			return false;
		if (getNews() == null) {
			if (other.news != null)
				return false;
		} else if (!getNews().equals(other.getNews()))
			return false;
		if (getText() == null) {
			if (other.text != null)
				return false;
		} else if (!getText().equals(other.getText()))
			return false;
		return true;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (getCommentId() == null) {
			if (other.getCommentId() != null)
				return false;
		} else if (!getCommentId().equals(other.getCommentId()))
			return false;
		if (getCreationDate() == null) {
			if (other.getCreationDate() != null)
				return false;
		} else if (!getCreationDate().equals(other.getCreationDate()))
			return false;
		if (getText() == null) {
			if (other.getText() != null)
				return false;
		} else if (!getText().equals(other.getText()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ",    text=	" +  text + ", creationDate="
				+ creationDate + "]";
	}
	
	

}
