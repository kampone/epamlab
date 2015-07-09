
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.AuthorService;


/**
 * @author Uladzislau_Kaminski
 *
 */

@ContextConfiguration(locations = {"/TestContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorServiceImplTest {

	@Mock
	private AuthorDAO mockAuthorDAO;
	
	@InjectMocks
	@Autowired
	private AuthorService authorService;
	

	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        assertNotNull(authorService);
    }
 
		
	@Test
	public void testCreate() throws ServiceException, DAOException {
	
		Long expected = 1L;
		Author author = new Author();
		when(mockAuthorDAO.create(author)).thenReturn(1L);
		Long actual = authorService.create(author);
		verify(mockAuthorDAO,times(1)).create(author);
		assertEquals(expected, actual);
		
	}

	
	@Test
	public void testRead() throws ServiceException, DAOException {
		Author author = new Author();
		when(authorService.read(anyLong())).thenReturn(author);
		Author actual = authorService.read(anyLong());
		verify(mockAuthorDAO,times(1)).read(anyLong());
		assertEquals(author, actual);
	}

	
	@Test
	public void testUpdate() throws ServiceException, DAOException {
		Author author = new Author();
		authorService.update(author);
		verify(mockAuthorDAO,times(1)).update(author);
	}

	
	@Test
	public void testDeleteAuthor() throws ServiceException, DAOException {
		Author author = new Author();
		authorService.delete(author);
		verify(mockAuthorDAO,times(1)).delete(author.getId());
	}

	
	@Test
	public void testDeleteLong() throws ServiceException, DAOException {
		Author author = new Author();
		authorService.delete(author.getId());
		verify(mockAuthorDAO,times(1)).delete(author.getId());
	}

	
	@Test
	public void testAttachAuthors() throws DAOException, ServiceException {
		
		authorService.attachAuthor(anyLong(), anyLong());
		verify(mockAuthorDAO,times(1)).attachAuthor(anyLong(), anyLong());
	}

	 

	@Test
	public void testDetachAuthors() throws ServiceException, DAOException {
		News news = new News();
		news.setId(anyLong());
		authorService.detachAuthor(news.getId());
		verify(mockAuthorDAO,times(1)).detachAuthor(news.getId());
}
	
	@Test
	public void testTakeAuthorByNewsId() throws ServiceException, DAOException{
		authorService.takeAuthorByNewsId(anyLong());
		verify(mockAuthorDAO,times(1)).takeAuthorByNewsId(anyLong());
	}

}
