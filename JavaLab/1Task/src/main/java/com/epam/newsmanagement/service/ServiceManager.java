/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface ServiceManager {
	
	public void addNews(News news, List<Tag> tagList) throws ServiceException;
	public void updateNews(News news, List<Tag> tagList, List<Comment> commentList) throws ServiceException;
	public void deleteNews(Long id) throws ServiceException;
	public void testMethod() throws ServiceException;
}
