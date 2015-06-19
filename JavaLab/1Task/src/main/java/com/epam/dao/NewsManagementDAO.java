/**
 * 
 */
package com.epam.dao;

import com.epam.entity.NewsManagementEntity;

/**
 * @author Uladzislau_Kaminski
 *	Interface to realize C.R.U.D. operations
 */
public interface NewsManagementDAO<T extends NewsManagementEntity> {
	
	public long create(T entity);
	
	public T read(long id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
}
