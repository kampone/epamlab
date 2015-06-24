package com.epam.newsmanagement.test.service.tag;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.newsmanagement.exception.DAOException;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;

import static org.mockito.Mockito.*;

public class TagServiceImplTest {
	private static ApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("TestContext.xml");
	}

	@Test
	public void testCreate() throws DAOException {
		TagDAO mockTagDAO = mock(TagDAO.class);

		when(mockTagDAO.create(new Tag())).thenReturn(1L);
		assertEquals(1L, mockTagDAO.create(new Tag()));

	}

	@Test
	public void testRead() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

}
