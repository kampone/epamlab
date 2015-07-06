/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.CommentService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.ServiceManager;
import com.epam.newsmanagement.service.TagService;

/**
 * @author Uladzislau_Kaminski
 *
 */
@ContextConfiguration(locations = { "/TestContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceManagerImplTest {

	@Mock
	private NewsService mockNewsService;
	@Mock
	private CommentService mockCommentService;
	@Mock
	private TagService mockTagService;
	@Mock
	private AuthorService mockAuthorService;

	@InjectMocks
	@Autowired
	private ServiceManager serviceManager;

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		assertNotNull(serviceManager);
	}

	/**
	 * Test method for
	 * {@link com.epam.newsmanagement.service.impl.ServiceManagerImpl#addNews(com.epam.newsmanagement.entity.News, long, java.util.List)}
	 * .
	 * 
	 * @throws ServiceException
	 * @throws DAOException
	 */
	@Test
	public void testAddNews() throws ServiceException, DAOException {
		News news = new News();
		long idAuthor = 1L;
		List<Long> idTagList = new ArrayList<>();

		serviceManager.addNews(news, idAuthor, idTagList);

		verify(mockNewsService, times(1)).create(news);
		verify(mockTagService, times(1)).attachListTags(news.getId(), idTagList);
		verify(mockAuthorService, times(1)).attachAuthor(news.getId(), idAuthor);
	}



	/**
	 * Test method for
	 * {@link com.epam.newsmanagement.service.impl.ServiceManagerImpl#updateNews(com.epam.newsmanagement.entity.News, long, java.util.List, java.util.List)}
	 * .
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testUpdateNews() throws ServiceException, DAOException {
		News news = new News();
		long idAuthor = 1L;
		List<Long> idTagList = new ArrayList<>();
		
		serviceManager.updateNews(news, idAuthor, idTagList);
		
		verify(mockTagService, times(1)).detachTags(news.getId());
		verify(mockAuthorService, times(1)).detachAuthor(news.getId());
		verify(mockNewsService, times(1)).update(news);
		verify(mockTagService, times(1)).attachListTags(news.getId(), idTagList);
		verify(mockAuthorService, times(1)).attachAuthor(news.getId(), idAuthor);
	}

	/**
	 * Test method for
	 * {@link com.epam.newsmanagement.service.impl.ServiceManagerImpl#deleteNews(java.lang.Long)}
	 * .
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteNews() throws ServiceException, DAOException {
		long idNews = 1L;
		serviceManager.deleteNews(idNews);
		
		verify(mockTagService, times(1)).detachTags(idNews);
		verify(mockAuthorService, times(1)).detachAuthor(idNews);
		verify(mockCommentService, times(1)).deleteCommentsByNewsId(idNews);
		verify(mockNewsService, times(1)).delete(idNews);
	}

}
