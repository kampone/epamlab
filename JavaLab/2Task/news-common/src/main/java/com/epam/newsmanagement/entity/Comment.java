/**
 * 
 */
package com.epam.newsmanagement.entity;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class Comment {
	
	private Long commentId;
	
	private Long newsId;
	
	@Size(min=5, max =100)
	private String text;
	
	private Timestamp creationDate;

	public Comment() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return commentId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.commentId = id;
	}

	/**
	 * @return the idNews
	 */
	public Long getNewsId() {
		return newsId;
	}

	/**
	 * @param idNews the idNews to set
	 */
	public void setNewsId(Long idNews) {
		this.newsId = idNews;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((newsId == null) ? 0 : newsId.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		if (newsId == null) {
			if (other.newsId != null)
				return false;
		} else if (!newsId.equals(other.newsId))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", newsId=" + newsId + ", text=" + text + ", creationDate="
				+ creationDate + "]";
	}
	
	

}
