package com.epam.newsmanagement.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(Comment entity) throws DAOException {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Comment read(Long id) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		return session.load(Comment.class, id);
	}

	@Override
	public void update(Comment entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);

	}

	@Override
	public void delete(Comment entity) throws DAOException {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Long id) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = session.load(Comment.class, id);
		session.delete(comment);
	}

	@Override
	public List<Comment> getCommentsByNewsId(Long newsId) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		News news = session.load(News.class, newsId);
		return news.getComments();
	}

	@Override
	public void deleteCommentsByNewsId(Long newsId) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		News news = session.load(News.class, newsId);
		news.setComments(null);
	}

	@Override
	public void addCommentsForNews(List<Comment> commentList) throws DAOException {
		for (Comment comment : commentList) {
			create(comment);
		}
	}

}
