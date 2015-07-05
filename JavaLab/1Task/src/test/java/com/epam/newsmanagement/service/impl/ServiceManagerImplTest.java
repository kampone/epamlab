/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

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

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.CommentService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.TagService;

/**
 * @author Uladzislau_Kaminski
 *
 */
@ContextConfiguration(locations = { "/TestContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceManagerImplTest {

	@Mock
	private NewsDAO mockNewsDAO;
	@Mock
	private CommentDAO mockCommentDAO;
	@Mock
	private TagDAO mockTagDAO;
	@Mock
	private AuthorDAO mockAuthorDAO;

	@InjectMocks
	@Autowired
	private NewsService newsService;

	@InjectMocks
	@Autowired
	private CommentService commentService;

	@InjectMocks
	@Autowired
	private TagService tagService;

	@InjectMocks
	@Autowired
	private AuthorService authorService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		assertNotNull(newsService);
		assertNotNull(tagService);
		assertNotNull(commentService);
		assertNotNull(authorService);
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

		long idNews = newsService.create(news);
		tagService.attachListTags(idNews, idTagList);
		authorService.attachAuthors(idNews, idAuthor);

		verify(mockNewsDAO, times(1)).create(news);
		verify(mockTagDAO, times(1)).attachListTags(idNews, idTagList);
		verify(mockAuthorDAO, times(1)).attachAuthors(idNews, idAuthor);
	}

//	@Test
//	@ExpectedException(value = Exception.class)
//	public void testAddNewsTransaction() throws Exception {
//		News news = new News();
//		long idAuthor = 1L;
//		List<Long> idTagList = new ArrayList<>();
//		long idNews = 0L;
//		try {
//			idNews = newsService.create(news);
//			tagService.attachListTags(idNews, idTagList);
//			if(true) throw new Exception();
//			authorService.attachAuthors(idNews, idAuthor);
//		} finally {
//			verify(mockNewsDAO, times(1)).create(news);
//			verify(mockTagDAO, times(1)).attachListTags(idNews, idTagList);
//			verify(mockAuthorDAO, times(0)).attachAuthors(idNews, idAuthor);
//		}
//	}

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
		
		tagService.detachTags(news.getId());
		authorService.detachAuthors(news.getId());
		newsService.update(news);
		authorService.attachAuthors(news.getId(), idAuthor);
		tagService.attachListTags(news.getId(), idTagList);
		
		verify(mockTagDAO, times(1)).detachTags(news.getId());
		verify(mockAuthorDAO, times(1)).detachAuthor(news.getId());
		verify(mockNewsDAO, times(1)).update(news);
		verify(mockTagDAO, times(1)).attachListTags(news.getId(), idTagList);
		verify(mockAuthorDAO, times(1)).attachAuthors(news.getId(), idAuthor);
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
		tagService.detachTags(idNews);
		authorService.detachAuthors(idNews);
		commentService.deleteCommentsByNewsId(idNews);
		newsService.delete(idNews);
		
		verify(mockTagDAO, times(1)).detachTags(idNews);
		verify(mockAuthorDAO, times(1)).detachAuthor(idNews);
		verify(mockCommentDAO, times(1)).deleteCommentsByNewsId(idNews);
		verify(mockNewsDAO, times(1)).delete(idNews);
	}

}
