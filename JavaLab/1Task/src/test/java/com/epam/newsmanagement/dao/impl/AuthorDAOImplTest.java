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

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/TestContext.xml" })
public class AuthorDAOImplTest extends DBTestCase {
	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private IDatabaseTester tester;

	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/authorDataSet.xml"));
		tester.setDataSet(dataSet);
		tester.setTearDownOperation(getTearDownOperation());
		tester.onSetup();
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

	@After
	public void tearDown() throws Exception {
		tester.onTearDown();
	}

	@Test
	public void testCreate() throws Exception {
		IDataSet expected = getDataSet();
		Author author = new Author();
		author.setName("testAuthor");
		authorDAO.create(author);
		IDataSet actual = tester.getConnection().createDataSet();
		assertEquals(expected.getTable("authors").getRowCount() + 1, actual.getTable("authors").getRowCount());

	}

	@Test
	public void testRead() throws Exception {
		long idAuthor = 1L;
		Author author = authorDAO.read(idAuthor);
		assertEquals(idAuthor, author.getId());
		assertEquals("Vasja", author.getName());
	}

	@Test
	public void testUpdate() throws DAOException {
		long idAuthor = 1L;
		String nameAuthor = "Dzmitri";
		Author author = new Author();
		author.setId(idAuthor);
		author.setName(nameAuthor);
		authorDAO.update(author);
		Author actual = authorDAO.read(idAuthor);
		assertEquals(idAuthor, actual.getId());
		assertEquals(nameAuthor, actual.getName());
	}

	@Test
	public void testDeleteAuthor() throws DAOException {
		long idAuthor = 1L;
		Author author = authorDAO.read(idAuthor);
		assertNull(author.getExpired());
		authorDAO.delete(author);
		author = authorDAO.read(idAuthor);
		assertNotNull(author.getExpired());

	}

	@Test
	public void testDeleteById() throws DAOException {
		long idAuthor = 1L;
		Author author = authorDAO.read(idAuthor);
		assertNull(author.getExpired());
		authorDAO.delete(author.getId());
		author = authorDAO.read(idAuthor);
		assertNotNull(author.getExpired());
	}

	@Test
	public void testAttachAuthors() throws Exception {
		IDataSet expected = getDataSet();
		long idAuthor = 1L;
		long idNews = 1L;
		authorDAO.attachAuthor(idNews, idAuthor);
		IDataSet actual = tester.getConnection().createDataSet();
		assertEquals(expected.getTable("news_authors").getRowCount() + 1,
				actual.getTable("news_authors").getRowCount());
	}

	@Test
	public void testDetachAuthors() throws Exception {
		IDataSet expected = getDataSet();
		long idNews = 2L;
		authorDAO.detachAuthor(idNews);
		IDataSet actual = tester.getConnection().createDataSet();
		assertEquals(expected.getTable("news_authors").getRowCount() - 1,
				actual.getTable("news_authors").getRowCount());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/authorDataSet.xml"));
	}

}
