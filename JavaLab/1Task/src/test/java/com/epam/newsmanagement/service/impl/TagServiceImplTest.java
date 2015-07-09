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
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        assertNotNull(tagService);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#create(com.epam.newsmanagement.entity.Tag)}.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testCreate() throws DAOException, ServiceException {
		Long expected = 1L;
		Tag tag = mock(Tag.class);
		when(mockTagDAO.create(tag)).thenReturn(1L);
		Long actual = tagService.create(tag);
		verify(mockTagDAO,times(1)).create(tag);
		assertEquals(expected, actual);	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#read(Long)}.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testRead() throws DAOException, ServiceException {
		Tag tag = new Tag();
		when(tagService.read(anyLong())).thenReturn(tag);
		Tag actual = tagService.read(anyLong());
		verify(mockTagDAO,times(1)).read(anyLong());
		assertEquals(tag, actual);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#update(com.epam.newsmanagement.entity.Tag)}.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testUpdate() throws DAOException, ServiceException {
		Tag tag = new Tag();
		tagService.update(tag);
		verify(mockTagDAO,times(1)).update(tag);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#delete(com.epam.newsmanagement.entity.Tag)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteTag() throws ServiceException, DAOException {
		Tag tag = new Tag();
		tagService.delete(tag);
		verify(mockTagDAO,times(1)).delete(tag);
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#delete(java.lang.Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteById() throws ServiceException, DAOException {
		Tag tag = new Tag();
		tagService.delete(tag.getId());
		verify(mockTagDAO,times(1)).delete(tag.getId());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#attachTags(Long, Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testAttachTags() throws ServiceException, DAOException {
		tagService.attachTags(anyLong(), anyLong());
		verify(mockTagDAO,times(1)).attachTags(anyLong(), anyLong());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#detachTags(Long)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDetachTags() throws ServiceException, DAOException {
		tagService.detachTags(anyLong());
		verify(mockTagDAO,times(1)).detachTags(anyLong());
	}

	/**
	 * Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#attachListTags(Long, java.util.List)}.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testAttachListTags() throws ServiceException, DAOException {
		tagService.attachListTags(anyLong(), anyList());
		verify(mockTagDAO,times(1)).attachListTags(anyLong(), anyList());
	}
	
	/**	 
	 *  Test method for {@link com.epam.newsmanagement.service.impl.TagServiceImpl#takeNewsTags(Long)}.
	 * @throws ServiceException
	 * @throws DAOException
	 */
	@Test
	public void testTakeNewsTags() throws ServiceException, DAOException{
		tagService.takeNewsTags(anyLong());
		verify(mockTagDAO,times(1)).takeNewsTags(anyLong());
	}

}
