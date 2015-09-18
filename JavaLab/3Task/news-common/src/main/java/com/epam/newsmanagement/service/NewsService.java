/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsPage;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 *
 */

public interface NewsService extends NewsManagementService<News> {
	/**
	 * Return news which satisfy search criteria
	 * 
	 * @param searchCriteria
	 *            search criteria
	 * @param startIndex
	 *            Start index of news
	 * @param lastIndex
	 *            Last index of news to return(to return all news you can use
	 *            {@link java.lang.Integer.MAX_VALUE} and
	 *            {@link java.lang.Integer.MAX_VALUE} )
	 * @return list of news
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws ServiceException;

	/**
	 * Return number of news which satisfy search criteria
	 * 
	 * @param searchCriteria
	 *            {@link SearchCriteria}
	 * @return number of {@link News}
	 * @throws DAOException
	 * @throws ServiceException
	 */
	int getNewsNumber(SearchCriteria searchCriteria) throws ServiceException;

	/**
	 * Return index of news according list of news satisfied SearchCriteria
	 * 
	 * @param searchCriteria
	 * @param newsId
	 * @return index of news according list of news satisfied SearchCriteria
	 * @throws ServiceException
	 */
	int findIndex(SearchCriteria searchCriteria, Long newsId) throws ServiceException;

	/**
	 * Return NewsPage by Id
	 * 
	 * @param newsId
	 * @return NewsPage by Id
	 * @throws ServiceException
	 */
	NewsPage getNewsPage(Long newsId) throws ServiceException;

	/**
	 * Return News from NewsPage
	 * 
	 * @param newsPage
	 * @return News from NewsPage
	 * @throws ServiceException
	 */
	News getNews(NewsPage newsPage) throws ServiceException;

}
