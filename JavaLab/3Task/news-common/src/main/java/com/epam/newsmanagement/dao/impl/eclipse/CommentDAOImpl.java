package com.epam.newsmanagement.dao.impl.eclipse;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;
public class CommentDAOImpl implements CommentDAO {
	private final static Logger LOG = Logger.getLogger(CommentDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Comment entity) throws DAOException {
		LOG.debug("Creating Comment");
		entity.setCreationDate(new Timestamp(System.currentTimeMillis()));
		try {
			News news = entityManager.find(News.class, entity.getNews().getNewsId());
			entity.setNews(news);
			news.getCommentList().add(entity);
			entityManager.merge(news);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
		return entity.getCommentId();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
	 */
	@Override
	public Comment read(Long id) throws DAOException {
		LOG.debug("Reading Comment");
		try {
			return entityManager.find(Comment.class, id);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Comment entity) throws DAOException {
		LOG.debug("Updating comment");
		try {
			entityManager.merge(entity);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Comment entity) throws DAOException {
		delete(entity.getCommentId());
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		LOG.debug("Deleting Comment");
		try {
			entityManager.remove(entityManager.find(Comment.class, id));
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Does't need in this implementation
	 */
	@Override
	public void deleteCommentsByNewsId(Long newsId) throws DAOException {
	}

	/**
	 * Does't need in this implementation
	 */
	@Override
	public void addCommentsForNews(List<Comment> commentList) throws DAOException {
	}

}
