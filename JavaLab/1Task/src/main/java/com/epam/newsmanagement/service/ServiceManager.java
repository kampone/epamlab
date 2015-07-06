/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface ServiceManager {
	/**
	 * Create new news in transaction
	 * @param news that will be created
	 * @param idAuthor of news
	 * @param idTagList id list of news tags
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	public void addNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException;
	/**
	 * Update news in transaction
	 * @param news that will be update
	 * @param idAuthor of news
	 * @param idTagList id list of news tags
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	public void updateNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException;
	/**
	 * Delete news and its comments and detach author and tags by id 
	 * @param idNews that should be deleted
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	public void deleteNews(Long idNews) throws ServiceException;
}
