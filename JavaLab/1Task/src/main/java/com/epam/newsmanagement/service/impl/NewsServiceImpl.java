/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import org.apache.log4j.Logger;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class NewsServiceImpl implements NewsService {
	private static final Logger LOG = Logger.getLogger(NewsServiceImpl.class);

	private NewsDAO newsDAO;

	/**
	 * 
	 */
	public NewsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the newsDAO
	 */
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	/**
	 * @param newsDAO
	 *            the newsDAO to set
	 */
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.newsmanagement.service.NewsManagementService#create(java.lang
	 * .Object)
	 */
	@Override
	public long create(News entity) throws ServiceException {
		Long idNews = null;
		try {
			idNews = newsDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating News ", e);
			throw new ServiceException(" Exception during creating News ", e);
		}
		return idNews;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(long)
	 */
	@Override
	public News read(long id) throws ServiceException {
		News news = null;
		try {
			news = newsDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading News ", e);
			throw new ServiceException(" Exception during reading News ", e);
		}
		return news;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.newsmanagement.service.NewsManagementService#update(java.lang
	 * .Object)
	 */
	@Override
	public void update(News entity) throws ServiceException {
		try {
			newsDAO.update(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during updating News ", e);
			throw new ServiceException(" Exception during updating News ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.newsmanagement.service.NewsManagementService#delete(java.lang
	 * .Object)
	 */
	@Override
	public void delete(News entity) throws ServiceException {
		delete(entity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.newsmanagement.service.NewsManagementService#delete(java.lang
	 * .Long)
	 */
	@Override
	public void delete(Long id) throws ServiceException {
		try {
			newsDAO.delete(id);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting News ", e);
			throw new ServiceException(" Exception during deleting News ", e);
		}
	}

}