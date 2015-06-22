/**
 * 
 */
package com.epam.dao.news.impl;

import com.epam.dao.exception.DAOException;
import com.epam.dao.news.NewsDAO;
import com.epam.entity.News;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class NewsDAOImpl implements NewsDAO {

	/* (non-Javadoc)
	 * @see com.epam.dao.NewsManagementDAO#create(com.epam.entity.NewsManagementEntity)
	 */
	@Override
	public long create(News entity) throws DAOException {
		long id = 0;
		
		return id;
	}

	/* (non-Javadoc)
	 * @see com.epam.dao.NewsManagementDAO#read(long)
	 */
	@Override
	public News read(long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.epam.dao.NewsManagementDAO#update(com.epam.entity.NewsManagementEntity)
	 */
	@Override
	public void update(News entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.epam.dao.NewsManagementDAO#delete(com.epam.entity.NewsManagementEntity)
	 */
	@Override
	public void delete(News entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.epam.dao.news.NewsDAO#addNewsTags(long, long)
	 */
	@Override
	public void addNewsTags(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.epam.dao.news.NewsDAO#addNewsAuthors(long, long)
	 */
	@Override
	public void addNewsAuthors(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.epam.dao.news.NewsDAO#deleteNewsTags(long, long)
	 */
	@Override
	public void deleteNewsTags(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.epam.dao.news.NewsDAO#deleteNewsAuthors(long, long)
	 */
	@Override
	public void deleteNewsAuthors(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

}
