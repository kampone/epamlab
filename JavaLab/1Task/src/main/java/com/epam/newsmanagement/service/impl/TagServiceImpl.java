/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import org.apache.log4j.Logger;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.TagService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class TagServiceImpl implements TagService {
	private static final Logger LOG = Logger.getLogger(TagServiceImpl.class);
	private TagDAO tagDAO;

	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.service.NewsManagementService#create(com.epam.entity.
	 * NewsManagementEntity)
	 * 
	 * @return id of tag if it is created
	 * 
	 */
	@Override
	public long create(Tag entity) throws ServiceException {
		Long idTag = null;
		try {
			idTag = tagDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Tag " + e);
			throw new ServiceException(" Exception during creating Tag " + e);
		}
		return idTag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.service.NewsManagementService#read(long)
	 */
	@Override
	public Tag read(long id) throws ServiceException {
		Tag tag = null;
		try {
			tag = tagDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Tag " + e);
			throw new ServiceException(" Exception during reading Tag ", e);
		}
		return tag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.service.NewsManagementService#update(com.epam.entity.
	 * NewsManagementEntity)
	 */
	@Override
	public void update(Tag entity) throws ServiceException {
		try {
			tagDAO.update(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during update Tag " + e);
			throw new ServiceException("Exception during update Tag", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.service.NewsManagementService#delete(com.epam.entity.
	 * NewsManagementEntity)
	 */
	@Override
	public void delete(Tag entity) throws ServiceException {
		try {
			tagDAO.delete(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during delete Tag " + e);
			throw new ServiceException("Exception during delete Tag",e);
		}

	}
	
	public void delete(Long idTag) throws ServiceException {
		try {
			tagDAO.delete(idTag);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Tag " + e);
			throw new ServiceException("Exception during delete Tag", e);
		}

	}

	@Override
	public void attachTags(long idNews, long idTag) throws ServiceException {
		try {
			tagDAO.attachTags(idNews, idTag);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching Tag " + e);
			throw new ServiceException("Exception during attaching Tag", e);
		}
	}

	@Override
	public void detachTags(long idNews) throws ServiceException {
		try {
			tagDAO.detachTags(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during detaching Tag " + e);
			throw new ServiceException("Exception during detaching Tag", e);

		}
	}

}
