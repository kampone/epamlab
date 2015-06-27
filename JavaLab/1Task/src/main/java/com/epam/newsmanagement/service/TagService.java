/**
 * 
 */
package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsManagementService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface TagService extends NewsManagementService<Tag> {
	
	public void attachTags(long idNews, long idTag) throws ServiceException;

	public void detachTags(long idNews) throws ServiceException;

}
