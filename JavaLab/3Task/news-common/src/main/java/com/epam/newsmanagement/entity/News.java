package com.epam.newsmanagement.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "NEWS")
@SequenceGenerator(name = "NEWS_NEWS_ID_SEQ", sequenceName = "NEWS_NEWS_ID_SEQ", allocationSize = 1)
public class News {

	@Id
	@Column(name = "NEWS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_NEWS_ID_SEQ")
	private Long newsId;
	
	@Size(min=1, max=30)
	@Column(name = "TITLE")
	private String title;
	
	@Size(min=1, max=100)
	@Column(name = "SHORT_TEXT")
	private String shortText;
	
	@Size(min=1, max=2000)
	@Column(name = "FULL_TEXT")
	private String fullText;
	
	@Column(name = "CREATION_DATE")
	private Timestamp creationDate;
	
	@NotNull
    @Future
    @DateTimeFormat()
	@Column(name = "MODIFICATION_DATE")
    private Date modificationDate;
	
	@ManyToOne(optional=false)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="NEWS_AUTHORS", joinColumns=@JoinColumn(name="NEWS_ID"), inverseJoinColumns=@JoinColumn(name="AUTHOR_ID"))
	private Author author;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="NEWS_TAGS", joinColumns=@JoinColumn(name="NEWS_ID"), inverseJoinColumns=@JoinColumn(name="TAG_ID"))
	private List<Tag> tagList;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "news")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Comment> commentList;

	public News() {
	}


	/**
	 * @return the newsId
	 */
	public Long getNewsId() {
		return newsId;
	}


	/**
	 * @param newsId the newsId to set
	 */
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
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
	 * @param shortText the shortText to set
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
	 * @param fullText the fullText to set
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
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the tagList
	 */
	public List<Tag> getTagList() {
		return tagList;
	}

	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the commentList
	 */
	public List<Comment> getCommentList() {
		return commentList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((fullText == null) ? 0 : fullText.hashCode());
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((newsId == null) ? 0 : newsId.hashCode());
		result = prime * result + ((shortText == null) ? 0 : shortText.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (!(obj instanceof News))
			return false;
		News other = (News) obj;
		if (getFullText() == null) {
			if (other.getFullText() != null)
				return false;
		} else 
			
			if (!getFullText().equals(other.getFullText()))
			return false;
		if (getModificationDate() == null) {
			if (other.getModificationDate() != null)
				return false;
		} else if (!getModificationDate().equals(other.getModificationDate())){
			return false;
		}
		if (getNewsId() == null) {
			if (other.getNewsId() != null)
				return false;
		} else if (!getNewsId().equals(other.getNewsId()))
			return false;
		if (getShortText() == null) {
			if (other.getShortText() != null)
				return false;
		} else if (!getShortText().equals(other.getShortText()))
			return false;
		if (getTitle() == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!getTitle().equals(other.getTitle()))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", title=" + title + ", shortText=" + shortText + ", fullText=" + fullText
				+ ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + ", author=" + author
				+ ", tagList=" + tagList ;
	}

	
	
}
