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
	 * Return list of comments by idNews
	 * @param idNews News id
	 * @return List of comments by idNews
	 */
	 List<Comment> takeCommentsByNewsId(Long idNews) throws DAOException;
	/**
	 * Delete all comments by News id
	 * @param idNews News id
	 * @throws DAOException 
	 */
	 void deleteCommentsByNewsId(Long idNews) throws DAOException;
}
