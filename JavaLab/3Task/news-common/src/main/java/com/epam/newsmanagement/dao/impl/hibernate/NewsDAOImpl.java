package com.epam.newsmanagement.dao.impl.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
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
		return (News) sessionFactory.getCurrentSession().get(News.class, id);
	}

	@Override
	public void update(News entity) throws DAOException {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(News entity) throws DAOException {
		entity.setCommentList(null);
		sessionFactory.getCurrentSession().merge(entity);
		sessionFactory.getCurrentSession().delete(entity);
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

		// criteria.addOrder(Property.forName("commentsCount").desc());
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
		return getNews(searchCriteria, Integer.MIN_VALUE, Integer.MAX_VALUE).indexOf(news) + 1;
	}

	@Override
	public NewsPage getNewsPage(Long newsId) {
		News news = (News) sessionFactory.getCurrentSession().load(News.class, newsId);
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

	@Override
	public News getNews(NewsPage newsPage) {
		Session session = sessionFactory.getCurrentSession();
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
