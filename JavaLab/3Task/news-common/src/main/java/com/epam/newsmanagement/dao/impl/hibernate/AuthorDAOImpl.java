package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.DAOException;

public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(Author entity) throws DAOException {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Author read(Long id) throws DAOException {
		return sessionFactory.getCurrentSession().get(Author.class, id);
	}

	@Override
	public void update(Author entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Author entity) throws DAOException {
		sessionFactory.getCurrentSession().delete(entity);		
	}

	
	@Override
	public void delete(Long id) throws DAOException {
		Author author = sessionFactory.getCurrentSession().load(Author.class, id);
		author.setExpired(new Timestamp(new Date().getTime()));
	}

	@Override
	public void attachAuthorToNews(Long newsId, Long authorId) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachAuthorFromNews(Long newsId) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Author getAuthorByNewsId(Long newsId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> getAllAuthors() throws DAOException {
		return sessionFactory.getCurrentSession().createCriteria(Author.class).list();
	}

}
