/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.NewsService;

/**
 * @author Uladzislau_Kaminski
 *
 */
@ContextConfiguration(locations = {"/TestContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class NewsServiceImplTest {


	@Mock
	private NewsDAO mockNewsDAO;
	
	@InjectMocks
	@Autowired
	private NewsService newsService;
	

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        assertNotNull(newsService);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.NewsServiceImpl#create(com.epam.newsmanagement.entity.News)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testCreate() throws ServiceException, DAOException {
		News news = new News();
		newsService.create(news);
		verify(mockNewsDAO,times(1)).create(news);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.NewsServiceImpl#read(Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testRead() throws ServiceException, DAOException {
		Long idNews = 1L;
		newsService.read(idNews);
		verify(mockNewsDAO,times(1)).read(idNews);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.NewsServiceImpl#update(com.epam.newsmanagement.entity.News)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testUpdate() throws ServiceException, DAOException {
		News news = new News();
		newsService.update(news);
		verify(mockNewsDAO,times(1)).update(news);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.NewsServiceImpl#delete(com.epam.newsmanagement.entity.News)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteNews() throws ServiceException, DAOException {
		News news = new News();
		newsService.delete(news);
		verify(mockNewsDAO,times(1)).delete(news);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.NewsServiceImpl#delete(java.lang.Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteById() throws ServiceException, DAOException {
		News news = new News();
		newsService.delete(news.getId());
		verify(mockNewsDAO,times(1)).delete(news.getId());
	}

	@Test
	public void testGetNews() throws Exception {
		//TODO
		throw new RuntimeException("not yet implemented");
	}

	

}
