/**
 * 
 */
package com.epam.newsmanagement.entity;

import java.util.List;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class SearchCriteria {

	private List<Long> idTagList;
	private long idAuthor;
	
	public List<Long> getIdTagList() {
		return idTagList;
	}
	public void setIdTagList(List<Long> idTagList) {
		this.idTagList = idTagList;
	}
	public long getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(long idAuthor) {
		this.idAuthor = idAuthor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAuthor ^ (idAuthor >>> 32));
		result = prime * result + ((idTagList == null) ? 0 : idTagList.hashCode());
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
		SearchCriteria other = (SearchCriteria) obj;
		if (idAuthor != other.idAuthor)
			return false;
		if (idTagList == null) {
			if (other.idTagList != null)
				return false;
		} else if (!idTagList.equals(other.idTagList))
			return false;
		return true;
	}
	
	
}
