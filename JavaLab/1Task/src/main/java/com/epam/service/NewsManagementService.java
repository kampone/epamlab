/**
 * 
 */
package com.epam.service;

import com.epam.entity.NewsManagementEntity;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface NewsManagementService<T extends NewsManagementEntity> {
	
	public long create(T entity);
	
	public T read(long id);
	
	public void update(T entity);
	
	public void delete(T entity);
}
