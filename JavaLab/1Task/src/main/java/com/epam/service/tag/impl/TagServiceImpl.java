/**
 * 
 */
package com.epam.service.tag.impl;

import org.apache.log4j.Logger;

import com.epam.dao.exception.DAOException;
import com.epam.dao.tag.TagDAO;
import com.epam.entity.Tag;
import com.epam.service.exception.ServiceException;
import com.epam.service.tag.TagService;

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
	 * @return -1 if Tag not create and id of tag if it is created
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
			throw new ServiceException("Exception during read Tag by ID");
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

}
