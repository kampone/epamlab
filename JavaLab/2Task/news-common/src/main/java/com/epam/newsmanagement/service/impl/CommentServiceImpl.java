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
	
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#create(java.lang.Object)
	 */
	@Override
	public Long create(Comment entity) throws ServiceException {
		try {
			return commentDAO.create(entity);
		} catch (DAOException e) {
			LOG.error(" Exception during creating Comment " , e);
			throw new ServiceException(" Exception during creating Comment " , e);
		}
	
	}

	
	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#read(java.lang.Long)
	 */
	@Override
	public Comment read(Long id) throws ServiceException {
		try {
			return commentDAO.read(id);
		} catch (DAOException e) {
			LOG.error(" Exception during reading Comment " , e);
			throw new ServiceException(" Exception during reading Comment " , e);
		}
	}


	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#update(java.lang.Object)
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
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(java.lang.Object)
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
	
	/**
	 * @see com.epam.newsmanagement.service.NewsManagementService#delete(java.lang.Long)
	 */
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
	public void deleteCommentsByNewsId(Long idNews) throws ServiceException {
		try {
			commentDAO.deleteCommentsByNewsId(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during deleting Comment by news id " , e);
			throw new ServiceException(" Exception during deleting Comment by new id " , e);
		}
		
	}

	/**
	 * @see com.epam.newsmanagement.service.CommentService#getCommentsByNewsId(java.lang.Long)
	 */
	@Override
	public List<Comment> getCommentsByNewsId(Long idNews) throws ServiceException {
		try {
			return commentDAO.getCommentsByNewsId(idNews);
		} catch (DAOException e) {
			LOG.error(" Exception during taking Comment by news id " , e);
			throw new ServiceException(" Exception during taking Comment by new id " , e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.service.CommentService#addCommnetsForNews(java.util.List)
	 */
	@Override
	public void addCommnetsForNews(List<Comment> commentList) throws ServiceException {
		try {
			commentDAO.addCommentsForNews(commentList);		
		} catch (DAOException e) {
			LOG.error(" Exception during adding comments fow news  " , e);
			throw new ServiceException(" Exception during adding comments fow news " , e);
		}
	}

}
