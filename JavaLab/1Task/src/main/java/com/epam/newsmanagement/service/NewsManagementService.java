/**
 * 
 */
package com.epam.newsmanagement.service;

import com.epam.newsmanagement.exception.ServiceException;

/**
 * @author Uladzislau_Kaminski
 *Interface to realize C.R.U.D. operations
 */
public interface NewsManagementService<T> {
	/**
	 * Create entity in database
	 * 
	 * @param entity
	 *            entity that should be created
	 * @return id of entity that is created in database
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	public Long create(T entity) throws ServiceException;
	/**
	 * Read entity from database
	 * 
	 * @param id
	 *            of entity that should be read
	 * @return Entity from database by id;
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	public T read(Long id) throws ServiceException;
	/**
	 * Update entity in database
	 * 
	 * @param entity
	 *            Entity that should be updated
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	public void update(T entity) throws ServiceException;
	/**
	 * Delete entity from database
	 * 
	 * @param entity
	 *            Entity that should be deleted
	 * @throws ServiceException
	 *             if trouble with connection in DAO layer
	 */
	public void delete(T entity) throws ServiceException;
	/**
	 * Delete entity from database
	 * @param id of entity
	 * @throws ServiceException if trouble with connection in DAO layer
	 */
	public void delete(Long id) throws ServiceException;

}
