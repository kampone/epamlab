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
	
	

	private List<Long> idTagList;
	private Long idAuthor;
	
	public SearchCriteria() {
		idTagList = new ArrayList<>();
	}

	public List<Long> getIdTagList() {
		return idTagList;
	}
	public void setIdTagList(List<Long> idTagList) {
		this.idTagList = idTagList;
	}
	public Long getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAuthor == null) ? 0 : idAuthor.hashCode());
		result = prime * result + ((idTagList == null) ? 0 : idTagList.hashCode());
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
		if (idAuthor == null) {
			if (other.idAuthor != null)
				return false;
		} else if (!idAuthor.equals(other.idAuthor))
			return false;
		if (idTagList == null) {
			if (other.idTagList != null)
				return false;
		} else if (!idTagList.equals(other.idTagList))
			return false;
		return true;
	}
	
	
}
