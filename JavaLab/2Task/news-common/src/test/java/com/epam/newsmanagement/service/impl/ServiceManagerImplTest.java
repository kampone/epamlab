/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyList;

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

import com.epam.newsmanagement.entity.Author;
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

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		assertNotNull(serviceManager);
	}

	
	@Test
	public void testAddNews() throws ServiceException, DAOException {
		News news = new News();
		List<Long> idTagList = new ArrayList<>();

		serviceManager.addNews(news, anyLong(), idTagList);

		verify(mockNewsService, times(1)).create(news);
		verify(mockTagService, times(1)).attachListTagsToNews(anyLong(), anyList());
		verify(mockAuthorService, times(1)).attachAuthorToNews(anyLong(), anyLong());
	}


	@Test
	public void testUpdateNews() throws ServiceException, DAOException {
		News news = new News();
		Long idNews = 1L;
		serviceManager.updateNews(news, idNews, anyList());

		verify(mockTagService, times(1)).detachTagsFromNews(anyLong());
		verify(mockAuthorService, times(1)).detachAuthorFromNews(anyLong());
		verify(mockNewsService, times(1)).update(news);
		verify(mockTagService, times(1)).attachListTagsToNews(anyLong(), anyList());
		verify(mockAuthorService, times(1)).attachAuthorToNews(anyLong(), anyLong());
	}

	
	@Test
	public void testDeleteNews() throws ServiceException, DAOException {
		serviceManager.deleteNews(anyLong());

		verify(mockTagService, times(1)).detachTagsFromNews(anyLong());
		verify(mockAuthorService, times(1)).detachAuthorFromNews(anyLong());
		verify(mockCommentService, times(1)).deleteCommentsByNewsId(anyLong());
		verify(mockNewsService, times(1)).delete(anyLong());
	}

	@Test
	public void testDeleteCommentsByNewsId() throws Exception {
		Long idNews = 1L;
		serviceManager.deleteCommentsByNewsId(idNews);
		verify(mockCommentService, times(1)).deleteCommentsByNewsId(anyLong());
	}

	@Test
	public void testAddCommentForNews() throws Exception {
		serviceManager.addCommentForNews(anyList());
		verify(mockCommentService, times(1)).addCommnetsForNews(anyList());
	}

	@Test
	public void testAttachListTagsForNews() throws Exception {
		List<Long> idTagList = new ArrayList<>();
		Long idNews = 1L;
		serviceManager.attachListTagsForNews(idNews, idTagList);
		verify(mockTagService, times(1)).attachListTagsToNews(idNews, idTagList);

	}

	@Test
	public void testAddNewAuthor() throws Exception {
		Author author = new Author();
		serviceManager.addNewAuthor(author);
		verify(mockAuthorService, times(1)).create(author);

	}

	@Test
	public void testGetSingleNews() throws Exception {
		serviceManager.getSingleNews(anyLong());
		verify(mockNewsService, times(1)).read(anyLong());
	}

	@Test
	public void testGetNews() throws Exception {
		int i = 1;
		serviceManager.getNews(null, i, i);
		verify(mockNewsService, times(1)).getNews(null, i, i);
	}


	@Test
	public void testGetNewsVO() throws Exception {
		Long idNews = 1L;
		serviceManager.getNewsVO(idNews);
		verify(mockNewsService, times(1)).read(idNews);
		verify(mockAuthorService, times(1)).getAuthorByNewsId(idNews);
		verify(mockTagService, times(1)).getNewsTags(idNews);
		verify(mockCommentService, times(1)).getCommentsByNewsId(idNews);
		}

}
