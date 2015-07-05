/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.util.List;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.DAOException;

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
	public List<Comment> takeCommentsByNewsId(long idNews) throws DAOException;
	/**
	 * Delete all comments by News id
	 * @param idNews News id
	 * @throws DAOException 
	 */
	public void deleteCommentsByNewsId(long idNews) throws DAOException;
}
