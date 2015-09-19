package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

public class AuthorDAOImpl implements AuthorDAO {
	private final static Logger LOG = Logger.getLogger(AuthorDAOImpl.class);

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
	public Long create(Author entity) throws DAOException {
		LOG.debug("Creating Author");
		try {
			return (Long) sessionFactory.getCurrentSession().save(entity);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
	 */
	@Override
	public Author read(Long id) throws DAOException {
		LOG.debug("Reading Author");
		try {
			return (Author) sessionFactory.getCurrentSession().load(Author.class, id);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Author entity) throws DAOException {
		LOG.debug("Updating Author");
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
	public void delete(Author entity) throws DAOException {
		entity.setExpired(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		LOG.debug("Deleting Author");
		try {
			Author author = (Author) sessionFactory.getCurrentSession().load(Author.class, id);
			author.setExpired(new Timestamp(System.currentTimeMillis()));
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Does't need in this implementation
	 */
	@Override
	public void attachAuthorToNews(Long newsId, Long authorId) throws DAOException {
	}

	/**
	 * Does't need in this implementation
	 */
	@Override
	public void detachAuthorFromNews(Long newsId) throws DAOException {
	}

	/**
	 * @see com.epam.newsmanagement.dao.AuthorDAO#getAuthorByNewsId(java.lang.Long)
	 */
	@Override
	public Author getAuthorByNewsId(Long newsId) throws DAOException {
		LOG.debug("Getting Author by news Id");
		try {
			Session session = sessionFactory.getCurrentSession();
			return ((News) session.load(News.class, newsId)).getAuthor();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.AuthorDAO#getAllAuthors()
	 */
	@Override
	public List<Author> getAllAuthors() throws DAOException {
		LOG.debug("Getting all Authors");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createCriteria(Author.class).list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}

	}

}
