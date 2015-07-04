package com.epam.newsmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/TestContext.xml" })
public class TagDAOImplTest extends DBTestCase {
//TODO POM properties and scope tests
	
	@Autowired
	private TagDAO tagDAO;

	@Autowired
	private IDatabaseTester tester;

	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = getDataSet();
		tester.setDataSet(dataSet);
		tester.setTearDownOperation(getTearDownOperation());
		tester.onSetup();
		
	}
	
	@After
	public void tearDown() throws Exception {
		tester.onTearDown();
	}

	@Test
	public void testCreate() throws Exception {
		IDataSet expected = getDataSet();
		Tag tag = new Tag();
		tag.setName("Tag");
		tagDAO.create(tag);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "tags" });
		assertEquals(expected.getTable("tags").getRowCount() + 1, actual.getTable("tags").getRowCount());

	}

	@Test
	public void testRead() throws DAOException {
		long idTag = 1L;
		Tag tag = tagDAO.read(idTag);
		assertEquals(idTag, tag.getId());
		assertEquals("test", tag.getName());
	}

	@Test
	public void testUpdate() throws DAOException {
		long idTag = 1L;
		String idName = "update";
		Tag tag = new Tag();
		tag.setId(idTag);
		tag.setName(idName);
		tagDAO.update(tag);
		Tag actual = tagDAO.read(idTag);
		assertEquals(idTag, actual.getId());
		assertEquals(idName, actual.getName());
	}

	@Test
	public void testDeleteById() throws Exception {
		IDataSet expected = getDataSet();
		long idTag = 1L;
		tagDAO.delete(idTag);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "tags" });
		assertEquals(expected.getTable("tags").getRowCount()-1, actual.getTable("tags").getRowCount());
	}

	@Test
	public void testDeleteTag() throws Exception {
		IDataSet expected = getDataSet();
		long idTag = 1L;
		Tag tag = new Tag();
		tag.setId(idTag);
		tagDAO.delete(tag);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "tags" });
		assertEquals(expected.getTable("tags").getRowCount()-1, actual.getTable("tags").getRowCount());
	}

	@Test
	public void testAttachTags() throws Exception {
		IDataSet expected = getDataSet();
		long idTag = 1L;
		long idNews = 1L;
		tagDAO.attachTags(idNews, idTag);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "news_tags" });
		assertEquals(expected.getTable("news_tags").getRowCount()+1, actual.getTable("news_tags").getRowCount());
	}

	@Test
	public void testDetachTags() throws Exception {
		IDataSet expected = getDataSet();
		long idNews = 2L;
		tagDAO.detachTags(idNews);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "news_tags" });
		assertEquals(expected.getTable("news_tags").getRowCount()-2, actual.getTable("news_tags").getRowCount());
	}

	@Test
	public void testAttachListTags() throws Exception {
		IDataSet expected = getDataSet();
		long idNews = 3L;
		List<Long> idTagList = new ArrayList<>();
		idTagList.add(1L);
		idTagList.add(2L);
		idTagList.add(3L);
		tagDAO.attachListTags(idNews, idTagList);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "news_tags" });
		assertEquals(expected.getTable("news_tags").getRowCount()+3, actual.getTable("news_tags").getRowCount());
	
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/tagDataSet.xml"));
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

}
