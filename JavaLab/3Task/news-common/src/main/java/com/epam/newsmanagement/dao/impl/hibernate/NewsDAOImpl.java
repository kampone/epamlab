package com.epam.newsmanagement.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.DAOException;

public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(News entity) throws DAOException {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public News read(Long id) throws DAOException {
		return sessionFactory.getCurrentSession().load(News.class, id);
	}

	@Override
	public void update(News entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(News entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException {
		News news = sessionFactory.getCurrentSession().load(News.class, id);		
		sessionFactory.getCurrentSession().delete(news);
	}

	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNewsNumber(SearchCriteria searchCriteria) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findIndex(SearchCriteria searchCriteria, Long newsId) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
