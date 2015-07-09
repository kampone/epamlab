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
		Tag tag = new Tag();
		String tagName = "Tag";
		tag.setName(tagName);
		Long idTag = tagDAO.create(tag);
		assertEquals(tagName, tagDAO.read(idTag).getName());

	}

	@Test
	public void testRead() throws DAOException {
		Long idTag = 1L;
		Tag tag = tagDAO.read(idTag);
		assertEquals(idTag, tag.getId());
		assertEquals("test", tag.getName());
	}

	@Test
	public void testUpdate() throws DAOException {
		Long idTag = 1L;
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
		Long idTag = 1L;
		tagDAO.delete(idTag);
		assertNull(tagDAO.read(idTag));
	}

	@Test
	public void testDeleteTag() throws Exception {
		Long idTag = 1L;
		Tag tag = new Tag();
		tag.setId(idTag);
		tagDAO.delete(tag);
		assertNull(tagDAO.read(idTag));
	}

	@Test
	public void testAttachTags() throws Exception {
		int size = 4;
		Long idTag = 1L;
		Long idNews = 1L;
		tagDAO.attachTags(idNews, idTag);
		assertEquals(size, tagDAO.takeNewsTags(idNews).size());
	}

	@Test
	public void testDetachTags() throws Exception {
		Long idNews = 2L;
		tagDAO.detachTags(idNews);
		assertTrue(tagDAO.takeNewsTags(idNews).isEmpty());
	}

	@Test
	public void testAttachListTags() throws Exception {
		Long idNews = 3L;
		int size = 3;
		List<Long> idTagList = new ArrayList<>();
		idTagList.add(1L);
		idTagList.add(2L);
		idTagList.add(3L);
		tagDAO.attachListTags(idNews, idTagList);
		assertEquals(size, tagDAO.takeNewsTags(idNews).size());

	}

	@Test
	public void testTakeNewsTags() throws Exception {
		Long idNews = 1L;
		int size = 3;
		List<Tag> tagList = tagDAO.takeNewsTags(idNews);
		assertEquals(size, tagList.size());
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
