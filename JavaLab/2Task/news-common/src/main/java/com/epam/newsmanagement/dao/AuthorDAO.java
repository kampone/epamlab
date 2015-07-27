/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.util.List;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface AuthorDAO extends NewsManagementDAO<Author> {

	/**
	 * Attach Author to News 
	 * @param newsId News id
	 * @param authorId Author id
	 * @throws DAOException if trouble with connection with database 
	 */
	void attachAuthorToNews(Long newsId, Long authorId) throws DAOException;
	/**
	 * Detach author from News by id
	 * @param newsId News id
	 * @throws DAOException if trouble with connection with database 
	 */
	void detachAuthorFromNews(Long newsId) throws DAOException;
	/**
	 * Return Author of news by its id
	 * @param newsId News id
	 * @return Author
	 * @throws DAOException  if trouble with connection with database
	 */
	Author getAuthorByNewsId(Long newsId) throws DAOException;
	
	
	/**Return all authors that can create news
	 * @return List<Author>
	 * @throws DAOException if trouble with connection with database
	 */
	List<Author> getAllAuthors() throws DAOException;	
}