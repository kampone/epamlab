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
	public void attachTags(long idNews, long idTag) throws DAOException;
	
	public void attachListTags(long idNews, List<Long> idTagList) throws DAOException;		

	public void detachTags(long idNews) throws DAOException;
	
	

}
