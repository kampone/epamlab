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
	 * Delete all comments by News id
	 * @param newsId News id
	 * @throws DAOException if trouble with connection with database 
	 */
	 void deleteCommentsByNewsId(Long newsId) throws DAOException;

	 /**
	  * Create list of comments for news
	  * @param commentList list of comments 
	  * @throws DAOException if trouble with connection with database 
	  */
	 void addCommentsForNews(List<Comment> commentList) throws DAOException;
}
