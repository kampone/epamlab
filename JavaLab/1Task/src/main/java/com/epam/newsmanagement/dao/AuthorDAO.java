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

	public void attachAuthors(long idNews, long idAuthor) throws DAOException;

	public void detachAuthors(long idNews) throws DAOException;
}
