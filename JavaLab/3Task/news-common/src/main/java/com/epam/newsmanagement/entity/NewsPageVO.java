package com.epam.newsmanagement.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NewsPageVO {
	@Valid
	private News news;
	@NotNull
	private Long authorId;
	private List<Long> tagIdList;

	public NewsPageVO() {
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public List<Long> getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(List<Long> tagIdList) {
		this.tagIdList = tagIdList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((news == null) ? 0 : news.hashCode());
		result = prime * result + ((tagIdList == null) ? 0 : tagIdList.hashCode());
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
		NewsPageVO other = (NewsPageVO) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (news == null) {
			if (other.news != null)
				return false;
		} else if (!news.equals(other.news))
			return false;
		if (tagIdList == null) {
			if (other.tagIdList != null)
				return false;
		} else if (!tagIdList.equals(other.tagIdList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsPageVO [news=" + news + ", authorId=" + authorId + ", tagIdList=" + tagIdList + "]";
	}
	
	

	
}
