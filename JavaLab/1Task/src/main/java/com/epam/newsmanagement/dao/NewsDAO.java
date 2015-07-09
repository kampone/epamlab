/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.util.List;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsDAO extends NewsManagementDAO<News> {
	/**
	 * Return news which satisfy search criteria
	 * @param searchCriteria  search criteria
	 * @param startIndex Start index of news
	 * @param lastIndex Last index of news to return(to return all news you can use java.lang.Integer.MAX_VALUE)
	 * @return list of news
	 * @throws DAOException if trouble with connection with database 
	 */
	List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException;
}
