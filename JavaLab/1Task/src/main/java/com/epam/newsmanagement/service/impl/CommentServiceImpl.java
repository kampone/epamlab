/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

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
	private static final Logger LOG = Logger.getLogger(CommentServiceImpl.class);

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
		Long idComment = null;
		try {
			idComment = commentDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Comment " , e);
			throw new ServiceException(" Exception during creating Comment " , e);
		}
		return idComment;
	
	}

	/** 
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(long)
	 */
	@Override
	public Comment read(long id) throws ServiceException {
		Comment comment = null;
		try {
			comment = commentDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Comment " , e);
			throw new ServiceException(" Exception during reading Comment " , e);
		}
		return comment;
	}

	/** 
	 * @see com.epam.newsmanagement.service.NewsManagementService#update(com.epam.newsmanagement.entity.NewsManagementEntity)
	 */
	@Override
	public void update(Comment entity) throws ServiceException {
		try {
			commentDAO.update(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during updating Comment " , e);
			throw new ServiceException(" Exception during updating Comment " , e);
		}

	}

	/** 
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(com.epam.newsmanagement.entity.NewsManagementEntity)
	 */
	@Override
	public void delete(Comment entity) throws ServiceException {
		try {
			commentDAO.delete(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting Comment " , e);
			throw new ServiceException(" Exception during deleting Comment " , e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			commentDAO.delete(id);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting Comment by id " , e);
			throw new ServiceException(" Exception during deleting Comment by id" , e);
		}
	}

	@Override
	public void deleteCommentsByNewsId(long idNews) throws ServiceException {
		try {
			commentDAO.deleteCommentsByNewsId(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting Comment by news id " , e);
			throw new ServiceException(" Exception during deleting Comment by new id " , e);
		}
		
	}

	@Override
	public List<Comment> takeCommentsByNewsId(long idNews) throws ServiceException {
		List<Comment> commentList = null;
		try {
			commentList = commentDAO.takeCommentsByNewsId(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during taking Comment by news id " , e);
			throw new ServiceException(" Exception during taking Comment by new id " , e);
		}
		return commentList;
	}

}
