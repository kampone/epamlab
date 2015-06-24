/**
 * 
 */
package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsDAO extends NewsManagementDAO<News> {
	public void attachTags(long idNews, long idTag) throws DAOException;

	public void attachAuthors(long idNews, long idTag) throws DAOException;

	public void deleteNewsTags(long idNews, long idTag) throws DAOException;

	public void deleteNewsAuthors(long idNews, long idTag) throws DAOException;
}
