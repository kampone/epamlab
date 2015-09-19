package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

public class CommentDAOImpl implements CommentDAO {
	private final static Logger LOG = Logger.getLogger(CommentDAOImpl.class);

	private SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Comment entity) throws DAOException {
		LOG.debug("Creating Comment");

		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		News news = (News) session.load(News.class, entity.getNews().getNewsId());
		entity.setNews(news);
		news.getCommentList().add(entity);
		entity.setCreationDate(new Timestamp(System.currentTimeMillis()));
		session.save(entity);
		return entity.getCommentId();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
	 */
	@Override
	public Comment read(Long id) throws DAOException {
		LOG.debug("Reading Comment");
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Comment) session.get(Comment.class, id);
		} catch (HibernateException e) {
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
			sessionFactory.getCurrentSession().update(entity);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}

	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Comment entity) throws DAOException {
		LOG.debug("Deleting Comment");

		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(entity);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		Comment comment = (Comment) session.get(Comment.class, id);
		session.delete(comment);
	}

	/**
	 * @see com.epam.newsmanagement.dao.CommentDAO#deleteCommentsByNewsId(java.lang.Long)
	 */
	@Override
	public void deleteCommentsByNewsId(Long newsId) throws DAOException {
		LOG.debug("Deleting Comment with News Id");

		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		News news = (News) session.load(News.class, newsId);
		for (Comment comment : news.getCommentList()) {
			session.delete(comment);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.CommentDAO#addCommentsForNews(java.util.List)
	 */
	@Override
	public void addCommentsForNews(List<Comment> commentList) throws DAOException {
		LOG.debug("Addition Comment List");

		for (Comment comment : commentList) {
			create(comment);
		}
	}

}
