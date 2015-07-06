/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import java.util.List;

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
	 */
	@Override
	public Long create(Tag entity) throws ServiceException {
		try {
			return tagDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Tag " + e);
			throw new ServiceException(" Exception during creating Tag " + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.service.NewsManagementService#read(Long)
	 */
	@Override
	public Tag read(Long id) throws ServiceException {
		try {
			return tagDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Tag " + e);
			throw new ServiceException(" Exception during reading Tag ", e);
		}
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
			throw new ServiceException("Exception during delete Tag", e);
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
	public void attachTags(Long idNews, Long idTag) throws ServiceException {
		try {
			tagDAO.attachTags(idNews, idTag);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching Tag " + e);
			throw new ServiceException("Exception during attaching Tag", e);
		}
	}

	@Override
	public void detachTags(Long idNews) throws ServiceException {
		try {
			tagDAO.detachTags(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during detaching Tag " + e);
			throw new ServiceException("Exception during detaching Tag", e);

		}
	}

	@Override
	public void attachListTags(Long idNews, List<Long> idTagList) throws ServiceException {
		try {
			tagDAO.attachListTags(idNews, idTagList);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching list Tag " + e);
			throw new ServiceException("Exception during attaching list Tag", e);
		}
	}

	@Override
	public List<Tag> takeNewsTags(Long idNews) throws ServiceException {
		try {
			return tagDAO.takeNewsTags(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during taking list Tag by news id " + e);
			throw new ServiceException("Exception during taking list Tag by news id", e);
		}
	}

}
