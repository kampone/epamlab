package com.epam.newsmanagement.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AUTHORS")
@SequenceGenerator(name = "AUTHORS_AUTHOR_ID_SEQ", sequenceName = "AUTHORS_AUTHOR_ID_SEQ", allocationSize = 1)
public class Author {

	@Id
	@Column(name = "AUTHOR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORS_AUTHOR_ID_SEQ")
	private Long authorId;
	
	@Column(name = "AUTHOR_NAME")
	@Size(min = 1, max = 30)
	private String name;
	
	@Column(name = "EXPIRED")	
	private Timestamp expired;

	public Author() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return authorId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.authorId = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the expired
	 */
	public Timestamp getExpired() {
		return expired;
	}

	/**
	 * @param expired
	 *            the expired to set
	 */
	public void setExpired(Timestamp expired) {
		this.expired = expired;
	}

	/**
	 * @return the authorId
	 */
	public Long getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((expired == null) ? 0 : expired.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (expired == null) {
			if (other.expired != null)
				return false;
		} else if (!expired.equals(other.expired))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", name=" + name + ", expired=" + expired + "]";
	}

	
	

	

}
