/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsPage;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface ServiceManager {
	/**
	 * Create new news in transaction
	 * 
	 * @param news
	 *            that will be created
	 * @return id of news
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	Long addNews(News news) throws ServiceException;

	/**
	 * Update news in transaction
	 * 
	 * @param news
	 *            that will be update
	 * @param authorId
	 *            of news
	 * @param tagIdList
	 *            id list of news tags
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	void updateNews(News news) throws ServiceException;

	/**
	 * Delete news and its comments and detach author and tags by id
	 * 
	 * @param newsId
	 *            that should be deleted
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	void deleteNews(Long newsId) throws ServiceException;

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
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws ServiceException;

	/**
	 * Return news with this id
	 * 
	 * @param newsId
	 *            News Id
	 * @return News with this id
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	News getSingleNews(Long newsId) throws ServiceException;

	/**
	 * Create new author in database
	 * 
	 * @param author
	 *            that should be created
	 * @return id of Author from database
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	Long addNewAuthor(Author author) throws ServiceException;

	/**
	 * Attach Tag list to News
	 * 
	 * @param newsId
	 *            News id
	 * @param tagIdList
	 *            List of Tag id
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	void attachListTagsForNews(Long newsId, List<Long> tagIdList) throws ServiceException;

	/**
	 * Add list of comments to news by news id
	 * 
	 * @param commentList
	 *            list of comments
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	void addCommentForNews(List<Comment> commentList) throws ServiceException;

	/**
	 * Delete all comments of news by news id
	 * 
	 * @param newsId
	 *            news id
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	void deleteCommentsByNewsId(Long newsId) throws ServiceException;

	/**
	 * Get News ValueObject with author, tags, comments
	 * 
	 * @param newsId
	 *            id of News
	 * @return NewsVO
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */


	List<Author> getAllAuthors() throws ServiceException;

	/**
	 * Return all tags that can be maked by news
	 * 
	 * @return List<Tag>
	 * @throws ServiceException
	 *             trouble in DAO layer
	 */
	List<Tag> getAllTags() throws ServiceException;

	/**
	 * Return number of news which satisfy Search Criteria
	 * 
	 * @param searchCriteria
	 *            {@link SearchCriteria}
	 * @return number of {@link News}
	 * @throws ServiceException
	 */
	int getNumberOfNews(SearchCriteria searchCriteria) throws ServiceException;

	/**
	 * Create comment
	 * 
	 * @param comment
	 *            {@link Comment}
	 * @return id of {@link Comment}
	 * @throws ServiceException
	 */
	Long createComment(Comment comment) throws ServiceException;

	/**
	 * Return index of news according list of news satisfied SearchCriteria
	 * @param searchCriteria
	 * @param newsId
	 * @return index of news according list of news satisfied SearchCriteria
	 * @throws ServiceException
	 */
	int findIndex(SearchCriteria searchCriteria, Long newsId) throws ServiceException;
	
	/**
	 * Delete comment with Id
	 * @param commentId Id
	 * @throws ServiceException
	 */
	void deleteComment(Long commentId) throws ServiceException;
	
	/**
	 * Return comment from DB with Id
	 * @param commentId Id
	 * @return comment from DB with Id
	 * @throws ServiceException
	 */
	Comment readComment(Long commentId) throws ServiceException;
	
	/**
	 * Create new Tag
	 * @param tag Tag
	 * @throws ServiceException
	 */
	void addNewTag(Tag tag) throws ServiceException;
	
	/**
	 * Update Tag
	 * @param tag Tag
	 * @throws ServiceException
	 */
	void updateTag(Tag tag) throws ServiceException;
	
	/**
	 * Update Author
	 * @param author Author
	 * @throws ServiceException
	 */
	void updateAuthor(Author author) throws ServiceException;
	
	/**
	 * Delete Author with Id
	 * @param idAuthor Id
	 * @throws ServiceException
	 */
	void deleteAuthor(Long idAuthor) throws ServiceException;
	
	/**
	 * Delete Tag with Id
	 * @param idTag Id
	 * @throws ServiceException
	 */
	void deleteTag(Long idTag) throws ServiceException;
	
	/**
	 * Return NewsPage by Id
	 * @param newsId
	 * @return NewsPage by Id
	 * @throws ServiceException
	 */
	NewsPage getNewsPage(Long newsId) throws ServiceException;
	/**
	 * Return News from NewsPage
	 * @param newsPage
	 * @return News from NewsPage
	 * @throws ServiceException
	 */
	News getNews(NewsPage newsPage) throws ServiceException;

	
}