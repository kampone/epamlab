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

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/TestContext.xml" })
public class CommentDAOImplTest extends DBTestCase{
	
	@Autowired
	private CommentDAO commentDAO;

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
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/commentDataSet.xml"));
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}
	@Test
	public void testCreate() throws Exception {
		long idNews = 1L;
		IDataSet expected = getDataSet();
		Comment comment = new Comment();
		comment.setText("comment");
		comment.setIdNews(idNews);
		commentDAO.create(comment);
		IDataSet actual = tester.getConnection().createDataSet(new String[] { "comments" });
		assertEquals(expected.getTable("comments").getRowCount() + 1, actual.getTable("comments").getRowCount());
	}

	@Test
	public void testRead() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testTakeCommentsByNewsId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteComment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteCommentsByNewsId() {
		fail("Not yet implemented"); // TODO
	}

}
