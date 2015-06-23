/**
 * 
 */
package com.epam.newsmanagement.dao.news;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.entity.News;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsDAO extends NewsManagementDAO<News> {
	public void addNewsTags(long idNews, long idTag) throws DAOException;

	public void addNewsAuthors(long idNews, long idTag) throws DAOException;

	public void deleteNewsTags(long idNews, long idTag) throws DAOException;

	public void deleteNewsAuthors(long idNews, long idTag) throws DAOException;
}
