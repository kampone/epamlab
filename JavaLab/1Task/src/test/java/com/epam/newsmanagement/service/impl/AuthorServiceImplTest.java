
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;

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
 
	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#create(com.epam.newsmanagement.entity.Author)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
		
	@Test
	public void testCreate() throws ServiceException, DAOException {
	
		long expected = 1L;
		Author author = new Author();
		when(mockAuthorDAO.create(author)).thenReturn(1L);
		long actual = authorService.create(author);
		verify(mockAuthorDAO,times(1)).create(author);
		assertEquals(expected, actual);
		
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#read(long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testRead() throws ServiceException, DAOException {
		Author author = new Author();
		when(authorService.read(anyLong())).thenReturn(author);
		Author actual = authorService.read(anyLong());
		verify(mockAuthorDAO,times(1)).read(anyLong());
		assertEquals(author, actual);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#update(com.epam.newsmanagement.entity.Author)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testUpdate() throws ServiceException, DAOException {
		Author author = new Author();
		authorService.update(author);
		verify(mockAuthorDAO,times(1)).update(author);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#delete(com.epam.newsmanagement.entity.Author)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteAuthor() throws ServiceException, DAOException {
		Author author = new Author();
		authorService.delete(author);
		verify(mockAuthorDAO,times(1)).delete(author.getId());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#delete(java.lang.Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteLong() throws ServiceException, DAOException {
		Author author = new Author();
		authorService.delete(author.getId());
		verify(mockAuthorDAO,times(1)).delete(author.getId());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#attachAuthors(long, long)}.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testAttachAuthors() throws DAOException, ServiceException {
		Author author = new Author();
		News news = mock(News.class);
		authorService.attachAuthors(news.getId(), author.getId());
		verify(mockAuthorDAO,times(1)).attachAuthors(news.getId(), author.getId());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.AuthorServiceImpl#detachAuthors(long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDetachAuthors() throws ServiceException, DAOException {
		News news = new News();
		authorService.detachAuthors(news.getId());
		verify(mockAuthorDAO,times(1)).detachAuthor(news.getId());
}

}
