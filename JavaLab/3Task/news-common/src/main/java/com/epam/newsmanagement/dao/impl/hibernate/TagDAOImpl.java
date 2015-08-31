package com.epam.newsmanagement.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

public class TagDAOImpl implements TagDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(Tag entity) throws DAOException {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Tag read(Long id) throws DAOException {
		return sessionFactory.getCurrentSession().get(Tag.class, id);
	}

	@Override
	public void update(Tag entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Tag entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachTagsToNews(Long newsId, Long tagId) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachListTagsToNews(Long newsId, List<Long> tagIdList) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void detachTagsFromNews(Long newsId) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tag> getNewsTags(Long idNews) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> getAllTags() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void detachTag(Long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

}
