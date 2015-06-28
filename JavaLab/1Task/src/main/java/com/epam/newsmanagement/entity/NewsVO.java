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

	public NewsVO() {
		tagsId = new ArrayList<Long>();
		commentsId = new ArrayList<Long>();
	}

	/**
	 * @return the idNews
	 */
	public long getIdNews() {
		return idNews;
	}

	/**
	 * @param idNews
	 *            the idNews to set
	 */
	public void setIdNews(long idNews) {
		this.idNews = idNews;
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
	public String getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * @return the tagsId
	 */
	public List<Long> getTagsId() {
		return tagsId;
	}

	/**
	 * @param tagsId
	 *            the tagsId to set
	 */
	public void setTagsId(List<Long> tagsId) {
		this.tagsId = tagsId;
	}

	/**
	 * @return the commentsId
	 */
	public List<Long> getCommentsId() {
		return commentsId;
	}

	/**
	 * @param commentsId
	 *            the commentsId to set
	 */
	public void setCommentsId(List<Long> commentsId) {
		this.commentsId = commentsId;
	}

	/**
	 * @param idComment
	 *            the id of comment to add to news
	 */
	public boolean addComment(Long idComment) {
		return commentsId.add(idComment);
	}
	
	/**
	 * @param idTag
	 *            the id of tag to add to news
	 */
	public boolean addTag(Long idTag) {
		return tagsId.add(idTag);
	}

}
