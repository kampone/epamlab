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
	 * @param idNews News id
	 * @param idTag Tag id
	 * @throws DAOException if trouble with connection with database 
	 */
	public void attachTags(long idNews, long idTag) throws DAOException;
	/**
	 *	Attach Tag list to News 
	 * @param idNews News id
	 * @param idTagList List of Tag id
	 * @throws DAOException if trouble with connection with database
	 */
	public void attachListTags(long idNews, List<Long> idTagList) throws DAOException;		
	/**
	 *	Detach all tags from News by id
	 * @param idNews News id
	 * @throws DAOException if trouble with connection with database
	 */
	public void detachTags(long idNews) throws DAOException;
	/**
	 * Return List of tags by news id
	 * @param idNews News id
	 * @return List of tags by news id
	 * @throws DAOException if trouble with connection with database
	 */
	public List<Tag> takeNewsTags(long idNews) throws DAOException;
	

}
