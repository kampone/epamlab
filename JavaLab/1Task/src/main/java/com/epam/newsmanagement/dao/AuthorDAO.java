/**
 * 
 */
package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface AuthorDAO extends NewsManagementDAO<Author> {

	/**
	 * Attach Author to News 
	 * @param idNews News id
	 * @param idAuthor Author id
	 * @throws DAOException if trouble with connection with database 
	 */
	public void attachAuthor(long idNews, long idAuthor) throws DAOException;
	/**
	 * Detach author from News by id
	 * @param idNews News id
	 * @throws DAOException if trouble with connection with database 
	 */
	public void detachAuthor(long idNews) throws DAOException;
}
