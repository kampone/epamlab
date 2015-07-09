/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
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
	 * @return id of news
	 * @throws ServiceException if trouble with connection in DAO layer
	 * @throws Exception 
	 */
	 Long addNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException;
	/**
	 * Update news in transaction
	 * @param news that will be update
	 * @param idAuthor of news
	 * @param idTagList id list of news tags
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	 void updateNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException;
	/**
	 * Delete news and its comments and detach author and tags by id 
	 * @param idNews that should be deleted
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	 void deleteNews(Long idNews) throws ServiceException;
	/**
	 * Return List of news that satisfy search criteria
	 * @param searchCriteria 
	 * @return List of news
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	 List<News> getNews(SearchCriteria searchCriteria , int startIndex, int lastIndex) throws ServiceException;
	/**
	 * Return news with this id
	 * @param idNews News Id
	 * @return News with this id
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	 News getSingleNews(Long idNews) throws ServiceException;
	/**
	 * Create new author in datebase
	 * @param author that should be created
	 * @return id of Author from database
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	 Long addNewAuthor(Author author) throws ServiceException;
	/**
	 *	Attach Tag list to News 
	 * @param idNews News id
	 * @param idTagList List of Tag id
	 * @throws ServiceException if trouble in DAO layer 
	 */
	 void attachListTagsForNews(Long idNews, List<Long> idTagList) throws ServiceException;		
	/**
	 * Add list of comments to news by news id
	 * @param idNews news id
	 * @param commentList list of comments
	 * @throws ServiceException if trouble in DAO layer 
	 */
	 void addCommentForNews(Long idNews, List<Comment> commentList) throws ServiceException;
	/**
	 * Delete all comments of news by news id
	 * @param idNews news id
	 * @throws ServiceException if trouble in DAO layer 
	 */ 
	 void deleteCommentsByNewsId(Long idNews) throws ServiceException;
}