/**
 * 
 */
package com.epam.newsmanagement.entity;

import java.sql.Timestamp;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class Comment {
	private Long idComment;
	private Long idNews;
	private String text;
	private Timestamp creationDate;

	public Comment() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return idComment;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idComment = id;
	}

	/**
	 * @return the idNews
	 */
	public Long getIdNews() {
		return idNews;
	}

	/**
	 * @param idNews the idNews to set
	 */
	public void setIdNews(Long idNews) {
		this.idNews = idNews;
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
		result = prime * result + ((idComment == null) ? 0 : idComment.hashCode());
		result = prime * result + ((idNews == null) ? 0 : idNews.hashCode());
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
		if (idComment == null) {
			if (other.idComment != null)
				return false;
		} else if (!idComment.equals(other.idComment))
			return false;
		if (idNews == null) {
			if (other.idNews != null)
				return false;
		} else if (!idNews.equals(other.idNews))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	

}
