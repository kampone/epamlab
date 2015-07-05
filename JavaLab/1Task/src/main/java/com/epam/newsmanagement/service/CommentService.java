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
	public void deleteCommentsByNewsId(long idNews) throws ServiceException;
	public List<Comment> takeCommentsByNewsId(long idNews) throws ServiceException;
}
