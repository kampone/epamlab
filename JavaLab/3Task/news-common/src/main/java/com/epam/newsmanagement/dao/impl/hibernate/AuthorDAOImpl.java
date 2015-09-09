package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

public class AuthorDAOImpl implements AuthorDAO {

	private SessionFactory sessionFactory;

	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long create(Author entity) throws DAOException {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Author read(Long id) throws DAOException {
		return (Author) sessionFactory.getCurrentSession().load(Author.class, id);
	}

	@Override
	public void update(Author entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Author entity) throws DAOException {
		entity.setExpired(new Timestamp(System.currentTimeMillis()));
	}

	
	@Override
	public void delete(Long id) throws DAOException {
		Author author = (Author) sessionFactory.getCurrentSession().load(Author.class, id);
		author.setExpired(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public void attachAuthorToNews(Long newsId, Long authorId) throws DAOException {
		/*Session session = sessionFactory.getCurrentSession();
		News news = (News) session.load(News.class, newsId);		
		Author author = (Author) session.load(Author.class, authorId);
		news.setAuthor(author);*/
	}

	@Override
	public void detachAuthorFromNews(Long newsId) throws DAOException {
	/*	Session session = sessionFactory.getCurrentSession();
		News news = (News) session.load(News.class, newsId);		
		news.setAuthor(null);	*/	
	}

	@Override
	public Author getAuthorByNewsId(Long newsId) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		return ((News) session.load(News.class, newsId)).getAuthor();
	}

	@Override
	public List<Author> getAllAuthors() throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Author.class).list();
	}

}
