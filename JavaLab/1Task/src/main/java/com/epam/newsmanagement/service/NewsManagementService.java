/**
 * 
 */
package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.NewsManagementEntity;
import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsManagementService<T> {

	public long create(T entity) throws ServiceException;

	public T read(long id) throws ServiceException;

	public void update(T entity) throws ServiceException;

	public void delete(T entity) throws ServiceException;
}
