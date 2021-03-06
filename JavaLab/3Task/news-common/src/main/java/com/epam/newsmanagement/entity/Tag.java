package com.epam.newsmanagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Uladzislau_Kaminski
 *
 */
@Entity
@Table(name = "TAGS")
@SequenceGenerator(name = "TAGS_TAG_ID_SEQ", sequenceName = "TAGS_TAG_ID_SEQ", allocationSize = 1)
public class Tag {
	
	@Id
	@Column(name = "TAG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAGS_TAG_ID_SEQ")
	private Long tagId;
	
	@Size(min=1, max=30)
	@Column(name = "TAG_NAME")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tagList")
	private List<News> newsList;

	/**
	 * @return the id
	 */
	public Long getId() {
		return tagId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.tagId = id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", name=" + name + "]";
	}


}
