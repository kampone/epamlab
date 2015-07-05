/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface TagService extends NewsManagementService<Tag> {
	/**
	 * Attach Tag to News 
	 * @param idNews News id
	 * @param idTag Tag id
	 * @throws ServiceException if trouble in DAO layer 
	 */
	public void attachTags(long idNews, long idTag) throws ServiceException;
	/**
	 *	Attach Tag list to News 
	 * @param idNews News id
	 * @param idTagList List of Tag id
	 * @throws ServiceException if trouble in DAO layer 
	 */
	public void attachListTags(long idNews, List<Long> idTagList) throws ServiceException;
	/**
	 *	Detach all tags from News by id
	 * @param idNews News id
	 * @throws ServiceException if trouble in DAO layer 
	 */
	public void detachTags(long idNews) throws ServiceException;
	/**
	 * Return List of tags by news id
	 * @param idNews News id
	 * @return List of tags by news id
	 * @throws ServiceException if trouble in DAO layer 
	 */
	public List<Tag> takeNewsTags(long idNews) throws ServiceException;

}
