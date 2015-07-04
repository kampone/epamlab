package com.epam.newsmanagement.entity;

import java.sql.Timestamp;

public class Author{
	private long idAuthor;
	private String name;
	private Timestamp expired;

	public Author() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return idAuthor;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idAuthor = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
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
	 * @param expired the expired to set
	 */
	public void setExpired(Timestamp expired) {
		this.expired = expired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expired == null) ? 0 : expired.hashCode());
		result = prime * result + (int) (idAuthor ^ (idAuthor >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Author other = (Author) obj;
		if (expired == null) {
			if (other.expired != null)
				return false;
		} else if (!expired.equals(other.expired))
			return false;
		if (idAuthor != other.idAuthor)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



}
