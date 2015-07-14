/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.TagService;

/**
 * @author Uladzislau_Kaminski
 *
 */
@ContextConfiguration(locations = {"/TestContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TagServiceImplTest {

	@Mock
	private TagDAO mockTagDAO;
	
	@InjectMocks
	@Autowired
	private TagService tagService;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        assertNotNull(tagService);
	}

	
	@Test
	public void testCreate() throws DAOException, ServiceException {
		Long expected = 1L;
		Tag tag = mock(Tag.class);
		when(mockTagDAO.create(tag)).thenReturn(1L);
		Long actual = tagService.create(tag);
		verify(mockTagDAO,times(1)).create(tag);
		assertEquals(expected, actual);	}

	@Test
	public void testRead() throws DAOException, ServiceException {
		Tag tag = new Tag();
		when(tagService.read(anyLong())).thenReturn(tag);
		Tag actual = tagService.read(anyLong());
		verify(mockTagDAO,times(1)).read(anyLong());
		assertEquals(tag, actual);
	}

	
	@Test
	public void testUpdate() throws DAOException, ServiceException {
		Tag tag = new Tag();
		tagService.update(tag);
		verify(mockTagDAO,times(1)).update(tag);
	}

	
	@Test
	public void testDeleteTag() throws ServiceException, DAOException {
		Tag tag = new Tag();
		tagService.delete(tag);
		verify(mockTagDAO,times(1)).delete(tag);
	}

	
	@Test
	public void testDeleteById() throws ServiceException, DAOException {
		Tag tag = new Tag();
		tagService.delete(tag.getId());
		verify(mockTagDAO,times(1)).delete(tag.getId());
	}

	
	@Test
	public void testAttachTags() throws ServiceException, DAOException {
		tagService.attachTagsToNews(anyLong(), anyLong());
		verify(mockTagDAO,times(1)).attachTagsToNews(anyLong(), anyLong());
	}

	
	@Test
		public void testDetachTagsFromNews() throws ServiceException, DAOException {
			tagService.detachTagsFromNews(anyLong());
			verify(mockTagDAO,times(1)).detachTagsFromNews(anyLong());
		}

	
	@Test
		public void testAttachListTagsToNews() throws ServiceException, DAOException {
			tagService.attachListTagsToNews(anyLong(), anyList());
			verify(mockTagDAO,times(1)).attachListTagsToNews(anyLong(), anyList());
		}
	
	
	@Test
		public void testGetNewsTags() throws ServiceException, DAOException{
			tagService.getNewsTags(anyLong());
			verify(mockTagDAO,times(1)).getNewsTags(anyLong());
		}

}
