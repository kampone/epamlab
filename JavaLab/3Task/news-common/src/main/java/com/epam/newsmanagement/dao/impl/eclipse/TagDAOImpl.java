package com.epam.newsmanagement.dao.impl.eclipse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;
public class TagDAOImpl implements TagDAO {
	private final static Logger LOG = Logger.getLogger(TagDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;


	
	
	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Tag entity) throws DAOException {
		LOG.debug("Creating Tag");
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
	public Tag read(Long id) throws DAOException {
		LOG.debug("Reading Tag");
		try {
			return entityManager.find(Tag.class, id);
		} catch (PersistenceException e) {
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
			entityManager.merge(entity);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Tag entity) throws DAOException {
		delete(entity.getId());
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		LOG.debug("Deleting Tag");
		try {
			entityManager.remove(entityManager.find(Tag.class, id));
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Does't need in this implementation
	 */
	@Override
	public void attachTagsToNews(Long newsId, Long tagId) throws DAOException {
	}
	/**
	 * Does't need in this implementation
	 */
	@Override
	public void attachListTagsToNews(Long newsId, List<Long> tagIdList) throws DAOException {
	}
	/**
	 * Does't need in this implementation
	 */
	@Override
	public void detachTagsFromNews(Long newsId) throws DAOException {
	}

	@Override
	public List<Tag> getAllTags() throws DAOException {
		LOG.debug("Getting all Tags");
		try {
			return entityManager.createQuery("select t from Tag t", Tag.class).getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}


}
