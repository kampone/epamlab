/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface TagService extends NewsManagementService<Tag> {
	/**
	 * Attach Tag to News
	 * 
	 * @param newsId
	 *            News id
	 * @param tagId
	 *            Tag id
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	void attachTagsToNews(Long newsId, Long tagId) throws ServiceException;

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
	void attachListTagsToNews(Long newsId, List<Long> tagIdList) throws ServiceException;

	/**
	 * Detach all tags from News by id
	 * 
	 * @param newsId
	 *            News id
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	void detachTagsFromNews(Long newsId) throws ServiceException;

	/**
	 * Return List of tags by news id
	 * 
	 * @param newsId
	 *            News id
	 * @return List<Tag> by news id
	 * @throws ServiceException
	 *             if trouble in DAO layer
	 */
	List<Tag> getNewsTags(Long newsId) throws ServiceException;

	/**
	 * Return all tags that can be maked by news
	 * 
	 * @return List<Tag>
	 * @throws ServiceException
	 *             trouble in DAO layer
	 */
	List<Tag> getAllTags() throws ServiceException;

}
