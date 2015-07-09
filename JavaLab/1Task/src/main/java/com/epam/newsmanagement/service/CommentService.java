/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface CommentService extends NewsManagementService<Comment> {
	/**
	 * 	
	 * Delete all comments by news id 
	 * @param idNews News id
	 * @throws ServiceException if problem in DAO layer
	 */
	 void deleteCommentsByNewsId(Long idNews) throws ServiceException;
	/**
	 * 	Return all comments by news id
	 * @return all comments by news id 
	 * @param idNews News id
	 * @throws ServiceException if problem in DAO layer
	 */
	 List<Comment> takeCommentsByNewsId(Long idNews) throws ServiceException;
	 
	 /**
	  * Create list of comments for news
	  * @param commentList list of comments 
	  * @throws ServiceException if problem in DAO layer
	  */
	void addCommnetsForNews(List<Comment> commentList) throws ServiceException;
}
