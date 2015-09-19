package com.epam.newsmanagement.dao.impl.eclipse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsPage;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

public class NewsDAOImpl implements NewsDAO {
	private final static Logger LOG = Logger.getLogger(NewsDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(News entity) throws DAOException {
		LOG.debug("Creating news");
		try {
			entityManager.persist(entity);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
		return entity.getNewsId();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
	 */
	@Override
	public News read(Long id) throws DAOException {
		LOG.debug("Reading News");
		try {
			return entityManager.find(News.class, id);
		} catch (PersistenceException e) {
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
			entityManager.merge(entity);
		} catch (PersistenceException e) {
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
			News entity = entityManager.find(News.class, id);
			entityManager.remove(entity);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}

	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNews(com.epam.newsmanagement.entity.SearchCriteria, int, int)
	 */
	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException {
		LOG.debug("Getting News with SearchCriteria");

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<News> criteria = criteriaBuilder.createQuery(News.class);
		Root<News> newsRoot = criteria.from(News.class);
		criteria = buildCriteria(criteriaBuilder, criteria, newsRoot, searchCriteria);
		criteria.orderBy(criteriaBuilder.desc(newsRoot.get("commentCountView").get("commentCount")),
				criteriaBuilder.desc(newsRoot.get("modificationDate")));
		criteria.distinct(true);
		Query query = entityManager.createQuery(criteria);
		query.setFirstResult(startIndex < 1 ? 0 : startIndex - 1);
		query.setMaxResults(lastIndex);
		return query.getResultList();
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNewsNumber(com.epam.newsmanagement.entity.SearchCriteria)
	 */
	@Override
	public int getNewsNumber(SearchCriteria searchCriteria) throws DAOException {
		return getNews(searchCriteria, 0, Integer.MAX_VALUE).size();
	}

	private CriteriaQuery<News> buildCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<News> criteria,
			Root<News> newsRoot, SearchCriteria searchCriteria) {

		if (searchCriteria == null)
			return criteria;
		Join<News, Author> authors = newsRoot.join("author", JoinType.LEFT);
		Join<News, Tag> tags = newsRoot.join("tagList", JoinType.INNER);

		Predicate predicate = criteriaBuilder.disjunction();

		if (searchCriteria.getAuthorId() != null) {
			predicate = criteriaBuilder.equal(authors.get("authorId"), searchCriteria.getAuthorId());
			if (searchCriteria.getTagIdList() != null && !searchCriteria.getTagIdList().isEmpty()) {
				predicate = criteriaBuilder.and(predicate, tags.get("tagId").in(searchCriteria.getTagIdList()));
			}
			criteria.where(predicate);
		} else {
			if (searchCriteria.getTagIdList() != null && !searchCriteria.getTagIdList().isEmpty()) {
				predicate = criteriaBuilder.and(tags.get("tagId").in(searchCriteria.getTagIdList()));
				criteria.where(predicate);
			}
		}
		return criteria;
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#findIndex(com.epam.newsmanagement.entity.SearchCriteria, java.lang.Long)
	 */
	@Override
	public int findIndex(SearchCriteria searchCriteria, Long newsId) throws DAOException {
		LOG.debug("Getting News Index");
		News news = null;
		try {
			news = entityManager.find(News.class, newsId);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
		return getNews(searchCriteria, Integer.MIN_VALUE, Integer.MAX_VALUE).indexOf(news) + 1;
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNewsPage(java.lang.Long)
	 */
	@Override
	public NewsPage getNewsPage(Long newsId) throws DAOException {
		LOG.debug("Getting NewsPage");
		News news = null;
		try {
			news = entityManager.find(News.class, newsId);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNews(com.epam.newsmanagement.entity.NewsPage)
	 */
	@Override
	public News getNews(NewsPage newsPage) throws DAOException {
		LOG.debug("Getting NewsPage from News");
		News news = null;
		if (newsPage.getNewsId() != null) {
			try {
				news = entityManager.find(News.class, newsPage.getNewsId());
			} catch (PersistenceException e) {
				throw new DAOException(e);
			}
		} else {
			news = new News();
			news.setCreationDate(new Timestamp(System.currentTimeMillis()));
		}
		Author author = entityManager.find(Author.class, newsPage.getAuthorId());
		List<Tag> tagList = new ArrayList<>();
		news.setAuthor(author);
		news.setTitle(newsPage.getTitle());
		news.setShortText(newsPage.getShortText());
		news.setFullText(newsPage.getFullText());
		news.setModificationDate(newsPage.getModificationDate());
		for (Long tagId : newsPage.getTagIdList()) {
			tagList.add(entityManager.find(Tag.class, tagId));
		}
		news.setTagList(tagList);
		return news;
	}

}
