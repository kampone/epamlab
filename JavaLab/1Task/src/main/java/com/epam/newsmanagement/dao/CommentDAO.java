/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.util.List;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.Comment;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface CommentDAO extends NewsManagementDAO<Comment> {
	/**
	 * 
	 * @param idNews
	 * @return
	 */
	public List<Comment> takeCommentsByNewsId(long idNews);
	public void deleteCommentsByNewsId(long idNews);
}
