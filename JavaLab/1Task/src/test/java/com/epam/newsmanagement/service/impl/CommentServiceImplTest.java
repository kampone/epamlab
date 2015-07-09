/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.CommentService;

/**
 * @author Uladzislau_Kaminski
 *
 */
@ContextConfiguration(locations = {"/TestContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentServiceImplTest {

	@Mock
	private CommentDAO mockCommentDAO;
	
	@InjectMocks
	@Autowired
	private CommentService commentService;
	


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        assertNotNull(commentService);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.CommentServiceImpl#create(com.epam.newsmanagement.entity.Comment)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testCreate() throws ServiceException, DAOException {
		Comment comment = new Comment();
		commentService.create(comment);
		verify(mockCommentDAO,times(1)).create(comment);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.CommentServiceImpl#read(Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testRead() throws ServiceException, DAOException {
		Comment comment = new Comment();
		comment.setId(anyLong());
		commentService.read(comment.getId());
		verify(mockCommentDAO,times(1)).read(anyLong());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.CommentServiceImpl#update(com.epam.newsmanagement.entity.Comment)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testUpdate() throws ServiceException, DAOException {
		Comment comment = new Comment();
		commentService.update(comment);
		verify(mockCommentDAO,times(1)).update(comment);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.CommentServiceImpl#delete(com.epam.newsmanagement.entity.Comment)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteComment() throws ServiceException, DAOException {
		Comment comment = new Comment();
		commentService.delete(comment);
		verify(mockCommentDAO,times(1)).delete(comment);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.CommentServiceImpl#delete(java.lang.Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteLong() throws ServiceException, DAOException {
		Comment comment = new Comment();
		commentService.delete(comment.getId());
		verify(mockCommentDAO,times(1)).delete(comment.getId());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.CommentServiceImpl#deleteCommentsByNewsId(Long)}.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testDeleteCommentsByNewsId() throws DAOException, ServiceException {
		commentService.deleteCommentsByNewsId(Mockito.anyLong());
		verify(mockCommentDAO,times(1)).deleteCommentsByNewsId(Mockito.anyLong());
	}
	
	@Test
	public void testTakeCommnentsByNewsId() throws DAOException, ServiceException{
		commentService.takeCommentsByNewsId(Mockito.anyLong());
		verify(mockCommentDAO,times(1)).takeCommentsByNewsId(Mockito.anyLong());

	}

	@Test
	public void testAddCommnetsForNews() throws Exception {
		//TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testTakeCommentsByNewsId() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
