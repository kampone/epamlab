package com.epam.newsmanagement.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class News {
	private long idNews;
	private String title;
	private String shortText;
	private String fullText;
	private Timestamp creationDate;
	private Date modificationDate;

	public News() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return idNews;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.idNews = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the shortText
	 */
	public String getShortText() {
		return shortText;
	}

	/**
	 * @param shortText
	 *            the shortText to set
	 */
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	/**
	 * @return the fullText
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * @param fullText
	 *            the fullText to set
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	/**
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((fullText == null) ? 0 : fullText.hashCode());
		result = prime * result + (int) (idNews ^ (idNews >>> 32));
		result = prime
				* result
				+ ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result
				+ ((shortText == null) ? 0 : shortText.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		News other = (News) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (fullText == null) {
			if (other.fullText != null)
				return false;
		} else if (!fullText.equals(other.fullText))
			return false;
		if (idNews != other.idNews)
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (shortText == null) {
			if (other.shortText != null)
				return false;
		} else if (!shortText.equals(other.shortText))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
