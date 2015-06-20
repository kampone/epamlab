/**
 * 
 */
package com.epam.dao;

import com.epam.dao.exception.DAOException;
import com.epam.entity.NewsManagementEntity;

/**
 * @author Uladzislau_Kaminski Interface to realize C.R.U.D. operations
 */
public interface NewsManagementDAO<T extends NewsManagementEntity> {

	public long create(T entity) throws DAOException;

	public T read(long id) throws DAOException;

	public void update(T entity) throws DAOException;

	public void delete(T entity) throws DAOException;

}
