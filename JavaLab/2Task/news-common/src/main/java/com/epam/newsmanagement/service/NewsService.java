/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 *
 */

public interface NewsService extends NewsManagementService<News> {
	/**
	 * Return news which satisfy search criteria
	 * @param searchCriteria  search criteria
	 * @param startIndex Start index of news
	 * @param lastIndex Last index of news to return(to return all news you can use {@link java.lang.Integer.MAX_VALUE} and {@link java.lang.Integer.MAX_VALUE} )
	 * @return list of news
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer	 
	 */             
	List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws ServiceException;
}
