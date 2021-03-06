package com.epam.newsmanagement.dao.impl;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/TestContext.xml" })
public class NewsDAOImplTest extends DBTestCase {

	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private IDatabaseTester tester;

	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/newsDataSet.xml"));
		tester.setDataSet(dataSet);
		tester.setTearDownOperation(getTearDownOperation());
		tester.onSetup();

	}

	@After
	public void tearDown() throws Exception {
		tester.onTearDown();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/newsDataSet.xml"));
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	@Test
	public void testCreate() throws Exception {
		News news = new News();
		String title = "test_title";
		String shortText = "short text";
		String fullText = "full text";
		news.setTitle(title);
		news.setShortText(shortText);
		news.setFullText(fullText);
		Long idNews = newsDAO.create(news);
		News actualNews = newsDAO.read(idNews);
		assertEquals(title, actualNews.getTitle());
		assertEquals(shortText, actualNews.getShortText());
		assertEquals(fullText, actualNews.getFullText());
	}

	@Test
	public void testRead() throws DAOException {
		Long idNews = 1L;
		String title = "test_title0";
		String shortText = "short text0";
		String fullText = "full text0";
		String creationDate = "2012-12-20 12:20:10.0";
		String modificationDate = "2013-12-20";
		News news = newsDAO.read(idNews);
		
		assertEquals(idNews, news.getId());
		assertEquals(title, news.getTitle());
		assertEquals(shortText, news.getShortText());
		assertEquals(fullText, news.getFullText());
		assertEquals(creationDate, news.getCreationDate().toString());
		assertEquals(modificationDate, news.getModificationDate().toString());

	}

	@Test
	public void testUpdate() throws DAOException {
		Long idNews = 1L;
		String title = "test_title1";
		String shortText = "short text1";
		String fullText = "full text1";
		News news = newsDAO.read(idNews);
		news.setTitle(title);
		news.setShortText(shortText);
		news.setFullText(fullText);
		newsDAO.update(news);
		News actualNews = newsDAO.read(idNews);
		assertEquals(title, actualNews.getTitle());
		assertEquals(shortText, actualNews.getShortText());
		assertEquals(fullText, actualNews.getFullText());
	}

	@Test
	public void testDeleteNews() throws Exception {
		Long idNews = 1L;
		News news = new News();
		news.setId(idNews);
		newsDAO.delete(news);
		assertNull(newsDAO.read(idNews));
	}

	@Test
	public void testDeleteById() throws Exception {
		Long idNews = 1L;
		newsDAO.delete(idNews);
		assertNull(newsDAO.read(idNews));
	}

	@Test
	public void testGetNews() throws Exception {
		int size = 3;
		assertEquals(size, newsDAO.getNews(null, Integer.MIN_VALUE, Integer.MAX_VALUE).size());
	}

	@Test
	public void testGetNewsEmptySearchCriteria() throws Exception {
		int size = 3;
		assertEquals(size, newsDAO.getNews(new SearchCriteria(), Integer.MIN_VALUE, Integer.MAX_VALUE).size());
	}

	

}
