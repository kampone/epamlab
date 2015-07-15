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

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#create(java.lang.Object)
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

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(java.lang.Long)
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

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#update(java.lang.Object)
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

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(java.lang.Object)
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

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(java.lang.Long)
	 */
	public void delete(Long idTag) throws ServiceException {
		try {
			tagDAO.delete(idTag);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Tag " + e);
			throw new ServiceException("Exception during delete Tag", e);
		}

	}

	/**
	 * @see com.epam.newsmanagement.service.TagService#attachTags(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public void attachTagsToNews(Long idNews, Long idTag) throws ServiceException {
		try {
			tagDAO.attachTagsToNews(idNews, idTag);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching Tag " + e);
			throw new ServiceException("Exception during attaching Tag", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.TagService#detachTagsFromNews(java.lang.Long)
	 */
	@Override
	public void detachTagsFromNews(Long idNews) throws ServiceException {
		try {
			tagDAO.detachTagsFromNews(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during detaching Tag " + e);
			throw new ServiceException("Exception during detaching Tag", e);

		}
	}

	/**
	 * @see com.epam.newsmanagement.service.TagService#attachListTagsToNews(java.lang.Long,
	 *      java.util.List)
	 */
	@Override
	public void attachListTagsToNews(Long idNews, List<Long> idTagList) throws ServiceException {
		try {
			tagDAO.attachListTagsToNews(idNews, idTagList);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching list Tag " + e);
			throw new ServiceException("Exception during attaching list Tag", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.TagService#getNewsTags(java.lang.Long)
	 */
	@Override
	public List<Tag> getNewsTags(Long idNews) throws ServiceException {
		try {
			return tagDAO.getNewsTags(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during taking list Tag by news id " + e);
			throw new ServiceException("Exception during taking list Tag by news id", e);
		}
	}

}
