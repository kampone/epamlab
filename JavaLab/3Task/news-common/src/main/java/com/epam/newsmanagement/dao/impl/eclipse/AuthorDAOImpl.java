package com.epam.newsmanagement.dao.impl.eclipse;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

public class AuthorDAOImpl implements AuthorDAO {
	private final static Logger LOG = Logger.getLogger(AuthorDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Author entity) throws DAOException {
		LOG.debug("Creating Author");
		try {
			entityManager.persist(entity);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
		return entity.getId();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
	 */
	@Override
	public Author read(Long id) throws DAOException {
		LOG.debug("Reading Author");
		try {
			return entityManager.find(Author.class, id);
		} catch (PersistenceException e) {
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
			entityManager.merge(entity);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Author entity) throws DAOException {
		delete(entity.getId());
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		LOG.debug("Deleting Author");
		try {
			Author author = entityManager.find(Author.class, id);
			author.setExpired(new Timestamp(System.currentTimeMillis()));
			entityManager.merge(author);
		} catch (PersistenceException e) {
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
		News news = null;
		try {
			news = entityManager.find(News.class, newsId);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
		return news.getAuthor();
	}

	/**
	 * @see com.epam.newsmanagement.dao.AuthorDAO#getAllAuthors()
	 */
	@Override
	public List<Author> getAllAuthors() throws DAOException {
		LOG.debug("Getting all Authors");
		try {
			return entityManager.createQuery("select a from Author a", Author.class).getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

}
