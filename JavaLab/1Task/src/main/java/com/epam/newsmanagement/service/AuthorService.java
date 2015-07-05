/**
 * 
 */
package com.epam.newsmanagement.service;

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
	 * @param idNews News id
	 * @param idAuthor Author id
	 * @throws ServiceException if problem in DAO layer
	 */
	public void attachAuthor(long idNews, long idAuthor) throws ServiceException;

	/**
	 * 	
	 * Detach Author from News 
	 * @param idNews News id
	 * @throws ServiceException if problem in DAO layer
	 */
	public void detachAuthor(long idNews) throws ServiceException;
}