package com.epam.newsmanagement.dao.impl.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

public class TagDAOImpl implements TagDAO {
	private final static Logger LOG = Logger.getLogger(TagDAOImpl.class);

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
	public Long create(Tag entity) throws DAOException {
		LOG.debug("Creating Tag");
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
	public Tag read(Long id) throws DAOException {
		LOG.debug("Reading Tag");
		try {
			return (Tag) sessionFactory.getCurrentSession().get(Tag.class, id);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Tag entity) throws DAOException {
		LOG.debug("Updating Tag");
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
	public void delete(Tag entity) throws DAOException {
		LOG.debug("Deleting Tag");
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
		Tag tag;
		try {
			session = sessionFactory.getCurrentSession();
			tag = (Tag) session.load(Tag.class, id);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		session.delete(tag);
	}

	/**
	 * Does't need in this implementation
	 */
	@Override
	public void attachTagsToNews(Long newsId, Long tagId) throws DAOException {
	}

	/**
	 *  Does't need in this implementation
	 */  
	@Override
	public void attachListTagsToNews(Long newsId, List<Long> tagIdList) throws DAOException {
	}

	/**
	 * @see com.epam.newsmanagement.dao.TagDAO#detachTagsFromNews(java.lang.Long)
	 */
	@Override
	public void detachTagsFromNews(Long newsId) throws DAOException {
	}

	/**
	 * @see com.epam.newsmanagement.dao.TagDAO#getAllTags()
	 */
	@Override
	public List<Tag> getAllTags() throws DAOException {
		LOG.debug("Getting all Tags");
		try {
			return sessionFactory.getCurrentSession().createCriteria(Tag.class).list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Does't need in this implementation
	 */
	@Override
	public void detachTag(Long idTag) throws DAOException {
	}

}
