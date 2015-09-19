package com.epam.newsmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.ReadOnly;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "COMMENT_COUNT")
@Immutable
@ReadOnly
public class CommentCountView {
	@Id
	@Column(name = "VIEW_NEWS_ID")
	private Long viewNewsId;

	@Column(name = "COMMENT_COUNT")
	private Long commentCount;

	public CommentCountView() {

	}

	public CommentCountView(Long viewNewsId, Long commentCount) {
		super();
		this.viewNewsId = viewNewsId;
		this.commentCount = commentCount;
	}

	public Long getViewNewsId() {
		return viewNewsId;
	}

	public void setViewNewsId(Long viewNewsId) {
		this.viewNewsId = viewNewsId;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentCount == null) ? 0 : commentCount.hashCode());
		result = prime * result + ((viewNewsId == null) ? 0 : viewNewsId.hashCode());
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
		CommentCountView other = (CommentCountView) obj;
		if (commentCount == null) {
			if (other.commentCount != null)
				return false;
		} else if (!commentCount.equals(other.commentCount))
			return false;
		if (viewNewsId == null) {
			if (other.viewNewsId != null)
				return false;
		} else if (!viewNewsId.equals(other.viewNewsId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentCountView [viewNewsId=" + viewNewsId + ", commentCount=" + commentCount + "]";
	}

}
