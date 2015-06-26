/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.CommentService;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class CommentServiceImpl implements CommentService {
	private CommentDAO commentDAO;
	
	
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	/* (non-Javadoc)
	 * @see com.epam.newsmanagement.service.NewsManagementService#create(com.epam.newsmanagement.entity.NewsManagementEntity)
	 */
	@Override
	public long create(Comment entity) throws ServiceException {
		long id;
		try {
			id = commentDAO.create(entity);
		} catch (DAOException e) {
			throw new ServiceException(System.lineSeparator()+" Exception during creating Comment " + e);
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(long)
	 */
	@Override
	public Comment read(long id) throws ServiceException {
		Comment comment = null;
		try {
			comment = commentDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(" Exception during reading Comment " + e);
		}
		return comment;
	}

	/* (non-Javadoc)
	 * @see com.epam.newsmanagement.service.NewsManagementService#update(com.epam.newsmanagement.entity.NewsManagementEntity)
	 */
	@Override
	public void update(Comment entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(com.epam.newsmanagement.entity.NewsManagementEntity)
	 */
	@Override
	public void delete(Comment entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
