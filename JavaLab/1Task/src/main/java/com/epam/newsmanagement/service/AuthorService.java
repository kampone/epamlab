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
	 void attachAuthor(Long idNews, Long idAuthor) throws ServiceException;

	/**
	 * 	
	 * Detach Author from News 
	 * @param idNews News id
	 * @throws ServiceException if problem in DAO layer
	 */
	 void detachAuthor(Long idNews) throws ServiceException;
	
	/**
	 * Return Author of news by its id
	 * @param idNews News id
	 * @return Author
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	 Author takeAuthorByNewsId(Long idNews) throws ServiceException;
}