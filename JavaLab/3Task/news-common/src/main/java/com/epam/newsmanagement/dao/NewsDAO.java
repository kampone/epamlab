/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationParameter;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsPage;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsDAO extends NewsManagementDAO<News> {
	/**
	 * Return news which satisfy search criteria
	 * 
	 * @param searchCriteria
	 *            search criteria
	 * @param startIndex
	 *            Start index of news
	 * @param lastIndex
	 *            Last index of news to return(to return all news you can use
	 *            java.lang.Integer.MAX_VALUE)
	 * @return list of news
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException;

	/** 
	 * Return number of News satisfied SearchCriteria
	 * @param searchCriteria 
	 * @return number of News satisfied SearchCriteria
	 * @throws DAOException 
	 */
	int getNewsNumber(SearchCriteria searchCriteria) throws DAOException;

	/**
	 * Return index of news according list of news satisfied SearchCriteria
	 * @param searchCriteria
	 * @param newsId
	 * @return index of news according list of news satisfied SearchCriteria
	 * @throws DAOException
	 */
	int findIndex(SearchCriteria searchCriteria, Long newsId) throws DAOException;
	
	/**
	 * Return NewsPage by Id
	 * @param newsId
	 * @return NewsPage by Id
	 * @throws DAOException
	 */
	NewsPage getNewsPage(Long newsId) throws DAOException;
	
	/**
	 * Return News from NewsPage
	 * @param newsPage
	 * @return News from NewsPage
	 * @throws DAOException
	 */
	News getNews(NewsPage newsPage) throws DAOException;
	

}
