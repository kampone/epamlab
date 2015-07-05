/**
 * 
 */
package com.epam.newsmanagement.service;

import java.util.List;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface ServiceManager {

	public void addNews(News news, long idAuthor, List<Long> idTagList) throws ServiceException;

	public void updateNews(News news, long idAuthor, List<Long> idTagList) throws ServiceException;

	public void deleteNews(Long idNews) throws ServiceException;
}
