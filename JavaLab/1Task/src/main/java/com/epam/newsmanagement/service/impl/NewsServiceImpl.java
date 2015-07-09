/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
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

	/**
	 *
	 * 
	 * @see com.epam.newsmanagement.service.NewsManagementService#create(java.lang
	 *      .Object)
	 */
	@Override
	public Long create(News entity) throws ServiceException {
		try {
			return newsDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating News ", e);
			throw new ServiceException(" Exception during creating News ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(Long)
	 */
	@Override
	public News read(Long id) throws ServiceException {
		try {
			return newsDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading News ", e);
			throw new ServiceException(" Exception during reading News ", e);
		}
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
		try {
			newsDAO.delete(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting News ", e);
			throw new ServiceException(" Exception during deleting News ", e);
		}
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

	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startindex, int lastIndex) throws ServiceException {
		try {
			return newsDAO.getNews(searchCriteria, startindex, lastIndex);
		} catch (DAOException e) {
			LOG.error(" Exception during getting News with SearchCriteria ", e);
			throw new ServiceException(" Exception during getting News with SearchCriteria ", e);
		}
	}

}
