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
	
	public void attachAuthors(long idNews, long idAuthor) throws ServiceException;

	public void detachAuthors(long idNews) throws ServiceException;
}