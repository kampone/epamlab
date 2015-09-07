package com.epam.newsmanagement.dao.impl.hibernate;

import java.util.List;

import javax.persistence.criteria.Join;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

public class NewsDAOImpl implements NewsDAO {

	private SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long create(News entity) throws DAOException {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public News read(Long id) throws DAOException {
		return (News) sessionFactory.getCurrentSession().load(News.class, id);
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
		News news = (News) sessionFactory.getCurrentSession().load(News.class, id);
		sessionFactory.getCurrentSession().delete(news);
	}

	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(News.class);
		Conjunction conjunction = Restrictions.conjunction();

		Long authorId = null;
		List<Long> tagsIdList = null;

		if (searchCriteria != null) {
			authorId = searchCriteria.getAuthorId();
			tagsIdList = searchCriteria.getTagIdList();
		}
		if (authorId != null) {
			criteria.createAlias("author", "author");
			conjunction.add(Restrictions.eq("author.authorId", authorId));

		}
		if (tagsIdList != null && tagsIdList.size() != 0) {
			criteria.createAlias("tagList", "tag");
			conjunction.add(Restrictions.in("tag.tagId", tagsIdList));
		}
		criteria.add(conjunction);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(startIndex < 0 ? 0 : startIndex - 1);
		criteria.setMaxResults(lastIndex);
		
//		criteria.addOrder(Property.forName("commentsCount").desc());
		criteria.addOrder(Property.forName("modificationDate").desc());

		return criteria.list();
	}

	@Override
	public int getNewsNumber(SearchCriteria searchCriteria) throws DAOException {
		return getNews(searchCriteria, Integer.MIN_VALUE, Integer.MAX_VALUE).size();
	}

	@Override
	public int findIndex(SearchCriteria searchCriteria, Long newsId) throws DAOException {
		News news = (News) sessionFactory.getCurrentSession().load(News.class, newsId);
		return getNews(searchCriteria, Integer.MIN_VALUE, Integer.MAX_VALUE).indexOf(news)+1;
	}

}
