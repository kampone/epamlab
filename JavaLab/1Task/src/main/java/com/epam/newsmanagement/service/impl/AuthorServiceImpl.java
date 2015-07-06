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

	/**
	 * @return the authorDAO
	 */
	public AuthorDAO getAuthorDAO() {
		return authorDAO;
	}

	/**
	 * @param authorDAO
	 *            the authorDAO to set
	 */
	public void setAuthorDAO(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	@Override
	public Long create(Author entity) throws ServiceException {
		try {
			return authorDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Author ", e);
			throw new ServiceException(" Exception during creating Author ", e);
		}
	}

	@Override
	public Author read(Long id) throws ServiceException {
		try {
			return authorDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Author ", e);
			throw new ServiceException(" Exception during reading Author ", e);
		}
	}

	@Override
	public void update(Author entity) throws ServiceException {
		try {
			authorDAO.update(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during updating Author ", e);
			throw new ServiceException(" Exception during updating Author ", e);
		}
	}

	@Override
	public void delete(Author entity) throws ServiceException {
		delete(entity.getId());
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			authorDAO.delete(id);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting Author ", e);
			throw new ServiceException(" Exception during deleting Author ", e);
		}
	}

	@Override
	public void attachAuthor(Long idNews, Long idAuthor)
			throws ServiceException {
		try {
			authorDAO.attachAuthor(idNews, idAuthor);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching Author ", e);
			throw new ServiceException(" Exception during attaching Author ", e);

		}
	}

	@Override
	public void detachAuthor(Long idNews) throws ServiceException {
		try {
			authorDAO.detachAuthor(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during detaching Author ", e);
			throw new ServiceException(" Exception during detaching Author ", e);
		}
	}

}
