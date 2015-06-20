/**
 * 
 */
package com.epam.service;

import com.epam.entity.NewsManagementEntity;
import com.epam.service.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsManagementService<T extends NewsManagementEntity> {

	public long create(T entity) throws ServiceException;

	public T read(long id) throws ServiceException;

	public void update(T entity) throws ServiceException;

	public void delete(T entity) throws ServiceException;
}
