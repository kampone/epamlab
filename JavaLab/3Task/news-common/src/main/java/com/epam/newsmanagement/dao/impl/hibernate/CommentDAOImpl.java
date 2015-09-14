package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

public class CommentDAOImpl implements CommentDAO {

	private SessionFactory sessionFactory;
	
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long create(Comment entity) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.load(News.class, entity.getNews().getNewsId());
		entity.setNews(news);
		news.getCommentList().add(entity);
		entity.setCreationDate(new Timestamp(System.currentTimeMillis()));
		session.save(entity);
		return entity.getCommentId();
	}

	@Override
	public Comment read(Long id) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		return (Comment) session.get(Comment.class, id);
	}

	@Override
	public void update(Comment entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);

	}

	@Override
	public void delete(Comment entity) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}

	@Override
	public void delete(Long id) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = (Comment) session.get(Comment.class, id);
		session.delete(comment);
	}

	

	@Override
	public void deleteCommentsByNewsId(Long newsId) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.load(News.class, newsId);
		news.setCommentList(null);
	}

	@Override
	public void addCommentsForNews(List<Comment> commentList) throws DAOException {
		for (Comment comment : commentList) {
			create(comment);
		}
	}

}
