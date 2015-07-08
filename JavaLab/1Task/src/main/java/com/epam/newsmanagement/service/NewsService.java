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
	List<News> getNews(SearchCriteria searchCriteria, int startindex, int lastIndex) throws ServiceException;
}
