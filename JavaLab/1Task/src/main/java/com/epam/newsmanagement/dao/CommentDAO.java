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
	 * @throws DAOException if trouble with connection with database 
	 */
	 List<Comment> takeCommentsByNewsId(Long idNews) throws DAOException;
	/**
	 * Delete all comments by News id
	 * @param idNews News id
	 * @throws DAOException if trouble with connection with database 
	 */
	 void deleteCommentsByNewsId(Long idNews) throws DAOException;

	 /**
	  * Create list of comments for news
	  * @param idNews news id
	  * @param commentList list of comments 
	  * @throws DAOException if trouble with connection with database 
	  */
	 void addCommentsForNews(Long idNews, List<Comment> commentList) throws DAOException;
}
