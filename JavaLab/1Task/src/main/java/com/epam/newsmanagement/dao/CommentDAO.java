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
	 * @param idNews News id
	 * @return List of comments by idNews
	 */
	public List<Comment> takeCommentsByNewsId(long idNews);
	/**
	 * Delete all comments by News id
	 * @param idNews News id
	 */
	public void deleteCommentsByNewsId(long idNews);
}
