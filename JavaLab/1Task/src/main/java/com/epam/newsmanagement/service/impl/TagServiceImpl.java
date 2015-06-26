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
		long id = 0;
		try {
			id = tagDAO.create(entity);
		} catch (DAOException e) {
			throw new ServiceException(" Exception during creating Tag " + e);
		}
		return id;
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
			throw new ServiceException(System.lineSeparator()+" Exception during read Tag by ID ");
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
			throw new ServiceException("Exception during update Tag");
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
			throw new ServiceException("Exception during delete Tag");
		}

	}
	
	public void delete(Long idTag) throws ServiceException {
		try {
			tagDAO.delete(idTag);
		} catch (DAOException e) {
			throw new ServiceException("Exception during delete Tag");
		}

	}

}
