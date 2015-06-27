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
	public long create(Author entity) throws ServiceException {
		Long id = null;
		try {
			id = authorDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Author ", e);
			throw new ServiceException(" Exception during creating Author ", e);
		}
		return id;
	}

	@Override
	public Author read(long id) throws ServiceException {
		Author author = null;
		try {
			author = authorDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Author ", e);
			throw new ServiceException(" Exception during reading Author ", e);
		}
		return author;
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
	public void attachAuthors(long idNews, long idAuthor)
			throws ServiceException {
		try {
			authorDAO.attachAuthors(idNews, idAuthor);
		} catch (DAOException e) {
			LOG.error(" Exception during attaching Author ", e);
			throw new ServiceException(" Exception during attaching Author ", e);

		}
	}

	@Override
	public void detachAuthors(long idNews) throws ServiceException {
		try {
			authorDAO.detachAuthors(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during detaching Author ", e);
			throw new ServiceException(" Exception during detaching Author ", e);
		}
	}

}
