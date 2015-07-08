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
	List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException;
}
