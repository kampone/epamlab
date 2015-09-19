/**
 * 
 */
package com.epam.newsmanagement.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class SearchCriteria {

	private List<Long> tagIdList;
	private Long authorId;
	/**
	 * @return the tagIdList
	 */
	public List<Long> getTagIdList() {
		return tagIdList;
	}
	/**
	 * @param tagIdList the tagIdList to set
	 */
	public void setTagIdList(List<Long> tagIdList) {
		this.tagIdList = tagIdList;
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
		result = prime * result + ((tagIdList == null) ? 0 : tagIdList.hashCode());
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
		SearchCriteria other = (SearchCriteria) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (tagIdList == null) {
			if (other.tagIdList != null)
				return false;
		} else if (!tagIdList.equals(other.tagIdList))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchCriteria [tagIdList=" + tagIdList + ", authorId=" + authorId + "]";
	}

}
