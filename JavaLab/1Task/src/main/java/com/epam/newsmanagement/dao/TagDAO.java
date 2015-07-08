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
	 void attachTags(Long idNews, Long idTag) throws DAOException;
	/**
	 *	Attach Tag list to News 
	 * @param idNews News id
	 * @param idTagList List of Tag id
	 * @throws DAOException if trouble with connection with database
	 */
	 void attachListTags(Long idNews, List<Long> idTagList) throws DAOException;		
	/**
	 *	Detach all tags from News by id
	 * @param idNews News id
	 * @throws DAOException if trouble with connection with database
	 */
	 void detachTags(Long idNews) throws DAOException;
	/**
	 * Return List of tags by news id
	 * @param idNews News id
	 * @return List of tags by news id
	 * @throws DAOException if trouble with connection with database
	 */
	 List<Tag> takeNewsTags(Long idNews) throws DAOException;
	

}
