package com.epam.newsmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class NewsVO {
	private News news;
	private List<Long> tagsId;
	private List<Long> commentsId;
	private Long idAuthor;

	public NewsVO() {
		tagsId = new ArrayList<Long>();
		commentsId = new ArrayList<Long>();
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<Long> getTagsId() {
		return tagsId;
	}

	public void setTagsId(List<Long> tagsId) {
		this.tagsId = tagsId;
	}

	public List<Long> getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(List<Long> commentsId) {
		this.commentsId = commentsId;
	}

	public Long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentsId == null) ? 0 : commentsId.hashCode());
		result = prime * result + ((idAuthor == null) ? 0 : idAuthor.hashCode());
		result = prime * result + ((news == null) ? 0 : news.hashCode());
		result = prime * result + ((tagsId == null) ? 0 : tagsId.hashCode());
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
		NewsVO other = (NewsVO) obj;
		if (commentsId == null) {
			if (other.commentsId != null)
				return false;
		} else if (!commentsId.equals(other.commentsId))
			return false;
		if (idAuthor == null) {
			if (other.idAuthor != null)
				return false;
		} else if (!idAuthor.equals(other.idAuthor))
			return false;
		if (news == null) {
			if (other.news != null)
				return false;
		} else if (!news.equals(other.news))
			return false;
		if (tagsId == null) {
			if (other.tagsId != null)
				return false;
		} else if (!tagsId.equals(other.tagsId))
			return false;
		return true;
	}

}
