package com.epam.newsmanagement.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NewsVO {
	private long idNews;
	private String title;
	private String shortText;
	private String fullText;
	private Timestamp creationDate;
	private String modificationDate;
	private List<Long> tagsId;
	private List<Long> commentsId;
	private long idAuthor;

	public NewsVO() {
		tagsId = new ArrayList<Long>();
		commentsId = new ArrayList<Long>();
	}

	public long getIdNews() {
		return idNews;
	}

	public void setIdNews(long idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
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
		result = prime * result + ((commentsId == null) ? 0 : commentsId.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((fullText == null) ? 0 : fullText.hashCode());
		result = prime * result + (int) (idAuthor ^ (idAuthor >>> 32));
		result = prime * result + (int) (idNews ^ (idNews >>> 32));
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((shortText == null) ? 0 : shortText.hashCode());
		result = prime * result + ((tagsId == null) ? 0 : tagsId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (idAuthor != other.idAuthor)
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
		if (tagsId == null) {
			if (other.tagsId != null)
				return false;
		} else if (!tagsId.equals(other.tagsId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

	
	
	

}
