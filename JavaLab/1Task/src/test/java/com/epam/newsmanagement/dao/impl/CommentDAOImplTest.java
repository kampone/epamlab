package com.epam.newsmanagement.dao.impl;

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

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.DAOException;

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
		Long idNews = 1L;
		String commentText = "comment";
		Comment comment = new Comment();
		comment.setText(commentText);
		comment.setIdNews(idNews);
		Long idComment = commentDAO.create(comment);
		assertEquals(idComment, commentDAO.read(idComment).getId());
		assertEquals(commentText, commentDAO.read(idComment).getText());
	}

	@Test
	public void testRead() throws DAOException {
		Long idComment = 1L;
		Long idNews = 1L;
		String commentText = "hello comment0";
		String creationDate = "2013-11-20 12:20:10.0";
		Comment comment = commentDAO.read(idComment);
		assertEquals(idComment, comment.getId());
		assertEquals(idNews, comment.getIdNews());
		assertEquals(commentText, comment.getText());
		assertEquals(creationDate, comment.getCreationDate().toString());
		
	}

	@Test
	public void testUpdate() throws DAOException {
		Long idComment = 1L;
		Long idNews = 1L;
		String commentText = "hello comment1";
		Comment comment = new Comment();
		comment.setId(idComment);
		comment.setIdNews(idNews);
		comment.setText(commentText);
		commentDAO.update(comment);
		Comment actualComment = commentDAO.read(idComment);
		assertEquals(idComment, actualComment.getId());
		assertEquals(idNews, actualComment.getIdNews());
		assertEquals(commentText, actualComment.getText());
	}

	@Test
	public void testTakeCommentsByNewsId() throws Exception {
		Long idNews = 1L;
		int size = 3;
		List<Comment> commentList = commentDAO.takeCommentsByNewsId(idNews);
		assertEquals(size, commentList.size());
	
	}

	@Test
	public void testDeleteComment() throws Exception {
		Long idComment = 1L;
		Comment comment = new Comment();
		comment.setId(idComment);
		commentDAO.delete(comment);
		assertNull(commentDAO.read(idComment));
	}

	@Test
	public void testDeleteById() throws Exception {
		Long idComment = 1L;
		commentDAO.delete(idComment);
		assertNull(commentDAO.read(idComment));
		}

	@Test
	public void testDeleteCommentsByNewsId() throws Exception {
		Long idNews = 1L;
		commentDAO.deleteCommentsByNewsId(idNews);
		assertTrue(commentDAO.takeCommentsByNewsId(idNews).isEmpty());
	}

	@Test
	public void testAddCommentsForNews() throws Exception {
		//TODO
		throw new RuntimeException("not yet implemented");
	}

}
