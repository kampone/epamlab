/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.util.List;

import com.epam.newsmanagement.dao.NewsManagementDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 */
public interface TagDAO extends NewsManagementDAO<Tag> {
	/**
	 * Attach Tag to News
	 * 
	 * @param newsId
	 *            News id
	 * @param tagId
	 *            Tag id
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	void attachTagsToNews(Long newsId, Long tagId) throws DAOException;

	/**
	 * Attach Tag list to News
	 * 
	 * @param newsId
	 *            News id
	 * @param tagIdList
	 *            List of Tag id
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	void attachListTagsToNews(Long newsId, List<Long> tagIdList) throws DAOException;

	/**
	 * Detach all tags from News by id
	 * 
	 * @param newsId
	 *            News id
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	void detachTagsFromNews(Long newsId) throws DAOException;

	/**
	 * Return List of tags by news id
	 * 
	 * @param idNews
	 *            News id
	 * @return List of tags by news id
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	List<Tag> getNewsTags(Long idNews) throws DAOException;

	/**
	 * Return all tags from database
	 * 
	 * @return List<Tag>
	* @throws DAOException
	 *             if trouble with connection with database
	 */
	List<Tag> getAllTags() throws DAOException;

	void detachTag(Long idTag) throws DAOException;

}
