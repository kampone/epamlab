/**
 * 
 */
package com.epam.dao.news;

import com.epam.dao.NewsManagementDAO;
import com.epam.dao.exception.DAOException;
import com.epam.entity.News;

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
