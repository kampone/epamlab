package com.epam.newsmanagement.service.impl;

import org.apache.log4j.Logger;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.AuthorService;

public class AuthorServiceImpl implements AuthorService {
	private static final Logger LOG = Logger.getLogger(AuthorServiceImpl.class);

	private AuthorDAO authorDAO;

	public AuthorServiceImpl() {
	}

	
	public void setAuthorDAO(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}


	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#create(java.lang.Object)
	 */
	@Override
	public Long create(Author entity) throws ServiceException {
		try {
			return authorDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Author ", e);
			throw new ServiceException(" Exception during creating Author ", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(java.lang.Long)
	 */
	@Override
	public Author read(Long id) throws ServiceException {
		try {
			return authorDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Author ", e);
			throw new ServiceException(" Exception during reading Author ", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#update(java.lang.Object)
	 */
	@Override
	public void update(Author entity) throws ServiceException {
		try {
			authorDAO.update(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during updating Author ", e);
			throw new ServiceException(" Exception during updating Author ", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(java.lang.Object)
	 */
	@Override
	public void delete(Author entity) throws ServiceException {
		delete(entity.getId());
	}

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws ServiceException {
		try {
			authorDAO.delete(id);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting Author ", e);
			throw new ServiceException(" Exception during deleting Author ", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.AuthorService#attachAuthorToNews(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void attachAuthorToNews(Long idNews, Long idAuthor)
			throws ServiceException {
		try {
			authorDAO.attachAuthorToNews(idNews, idAuthor);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching Author ", e);
			throw new ServiceException(" Exception during attaching Author ", e);

		}
	}

	/**
	 * @see com.epam.newsmanagement.service.AuthorService#detachAuthorFromNews(java.lang.Long)
	 */
	@Override
	public void detachAuthorFromNews(Long idNews) throws ServiceException {
		try {
			authorDAO.detachAuthorFromNews(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during detaching Author ", e);
			throw new ServiceException(" Exception during detaching Author ", e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.AuthorService#getAuthorByNewsId(java.lang.Long)
	 */
	@Override
	public Author getAuthorByNewsId(Long idNews) throws ServiceException {
		try {
			return authorDAO.getAuthorByNewsId(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during taking Author by news id ", e);
			throw new ServiceException(" Exception during taking Author by news id ", e);
		}
	}

}
