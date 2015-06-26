package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.AuthorService;

public class AuthorServiceImpl implements AuthorService {
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
	 * @param authorDAO the authorDAO to set
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
			throw new ServiceException(" Exception during creating Author ",e);
		}
		return id;
	}

	@Override
	public Author read(long id) throws ServiceException {
		Author author = null;
		try {
			author = authorDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(" Exception during reading Author ",e);
		}
		return author;
	}

	@Override
	public void update(Author entity) throws ServiceException {
		try {
			authorDAO.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(" Exception during reading Author ",e);
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
			throw new ServiceException(" Exception during reading Author ", e);
		}
	}

}
