package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsPage;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

public class NewsDAOImpl implements NewsDAO {
	private final static Logger LOG = Logger.getLogger(NewsDAOImpl.class);

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
	public Long create(News entity) throws DAOException {
		LOG.debug("Creating news");

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
	public News read(Long id) throws DAOException {
		LOG.debug("Reading News");

		try {
			return (News) sessionFactory.getCurrentSession().get(News.class, id);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
	 */
	@Override
	public void update(News entity) throws DAOException {
		LOG.debug("Updating News");

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
	public void delete(News entity) throws DAOException {
		delete(entity.getNewsId());
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		LOG.debug("Deleting News");

		try {
			News news = (News) sessionFactory.getCurrentSession().load(News.class, id);
			sessionFactory.getCurrentSession().delete(news);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNews(com.epam.newsmanagement.entity.SearchCriteria,
	 *      int, int)
	 */
	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException {
		LOG.debug("Getting News with SearchCriteria");
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
			try {
				criteria.createAlias("author", "author");
				conjunction.add(Restrictions.eq("author.authorId", authorId));
			} catch (HibernateException e) {
				throw new DAOException(e);
			}

		}
		if (tagsIdList != null && tagsIdList.size() != 0) {
			try {
				criteria.createAlias("tagList", "tag");
				conjunction.add(Restrictions.in("tag.tagId", tagsIdList));
			} catch (HibernateException e) {
				throw new DAOException(e);
			}
		}
		criteria.add(conjunction);
		criteria = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("commentCountView",
				"count");
		criteria = criteria.addOrder(Order.desc("count.commentCount")).addOrder(Order.desc("modificationDate"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(startIndex < 0 ? 0 : startIndex - 1);
		criteria.setMaxResults(lastIndex);
		criteria.addOrder(Property.forName("modificationDate").desc());

		return criteria.list();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNewsNumber(com.epam.newsmanagement.entity.SearchCriteria)
	 */
	@Override
	public int getNewsNumber(SearchCriteria searchCriteria) throws DAOException {
		return getNews(searchCriteria, Integer.MIN_VALUE, Integer.MAX_VALUE).size();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#findIndex(com.epam.newsmanagement.entity.SearchCriteria,
	 *      java.lang.Long)
	 */
	@Override
	public int findIndex(SearchCriteria searchCriteria, Long newsId) throws DAOException {
		LOG.debug("Getting News Index");
		News news = null;
		try {
			news = (News) sessionFactory.getCurrentSession().load(News.class, newsId);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		return getNews(searchCriteria, Integer.MIN_VALUE, Integer.MAX_VALUE).indexOf(news) + 1;
	}

	/**
	 * @throws DAOException
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNewsPage(java.lang.Long)
	 */
	@Override
	public NewsPage getNewsPage(Long newsId) throws DAOException {
		LOG.debug("Getting NewsPage");
		News news = null;
		try {
			news = (News) sessionFactory.getCurrentSession().load(News.class, newsId);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		NewsPage newsPage = new NewsPage();
		List<Long> tagIdList = new ArrayList<>();
		newsPage.setTitle(news.getTitle());
		newsPage.setNewsId(news.getNewsId());
		newsPage.setShortText(news.getShortText());
		newsPage.setFullText(news.getFullText());
		newsPage.setAuthorId(news.getAuthor().getAuthorId());
		newsPage.setModificationDate(news.getModificationDate());
		newsPage.setCreationDate(news.getCreationDate());
		for (Tag tag : news.getTagList()) {
			tagIdList.add(tag.getId());
		}
		newsPage.setTagIdList(tagIdList);
		return newsPage;
	}

	/**
	 * @throws DAOException 
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNews(com.epam.newsmanagement.entity.NewsPage)
	 */
	@Override
	public News getNews(NewsPage newsPage) throws DAOException {
		LOG.debug("Getting NewsPage from News");
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
		News news = null;
		if (newsPage.getNewsId() != null) {
			news = (News) session.load(News.class, newsPage.getNewsId());
		} else {
			news = new News();
			news.setCreationDate(new Timestamp(System.currentTimeMillis()));
		}
		Author author = (Author) session.load(Author.class, newsPage.getAuthorId());
		List<Tag> tagList = new ArrayList<>();
		news.setAuthor(author);
		news.setTitle(newsPage.getTitle());
		news.setShortText(newsPage.getShortText());
		news.setFullText(newsPage.getFullText());
		news.setModificationDate(newsPage.getModificationDate());
		for (Long tagId : newsPage.getTagIdList()) {
			tagList.add((Tag) session.load(Tag.class, tagId));
		}
		news.setTagList(tagList);
		return news;
	}

}
