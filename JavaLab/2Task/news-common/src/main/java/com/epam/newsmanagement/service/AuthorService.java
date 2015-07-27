/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 * 
 */
public interface AuthorService extends NewsManagementService<Author> {
	/**
	 * 
	 * Attach Author to News
	 * 
	 * @param newsId
	 *            News id
	 * @param authorId
	 *            Author id
	 * @throws ServiceException
	 *             if problem in DAO layer
	 */
	void attachAuthorToNews(Long newsId, Long authorId) throws ServiceException;

	/**
	 * 
	 * Detach Author from News
	 * 
	 * @param newsId
	 *            News id
	 * @throws ServiceException
	 *             if problem in DAO layer
	 */
	void detachAuthorFromNews(Long newsId) throws ServiceException;

	/**
	 * Return Author of news by its id
	 * 
	 * @param newsId
	 *            News id
	 * @return Author
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	Author getAuthorByNewsId(Long newsId) throws ServiceException;

	/**
	 * Return all authors that can create news
	 * 
	 * @return List<Author>
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	List<Author> getAllAuthors() throws ServiceException;
}