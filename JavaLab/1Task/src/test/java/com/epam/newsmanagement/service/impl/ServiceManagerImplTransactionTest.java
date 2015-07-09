package com.epam.newsmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@ContextConfiguration(locations = { "/TestContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceManagerImplTransactionTest {
	
	@Autowired
	private ServiceManager serviceManager;  
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testAddNews() throws ServiceException, Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		List<Long> list = new ArrayList<>();
		searchCriteria.setIdAuthor(1L);
		list.add(1l);
		list.add(2l);
		list.add(3l);
//		list.add(4l);
		searchCriteria.setIdTagList(list);
		System.out.println(serviceManager.getNews(searchCriteria, 1, 10));
		}
//SELECT  res.news_id, res.title, res.short_text, res.full_text, res.creation_date, res.modification_date FROM ( SELECT row_number() OVER (ORDER BY nc.comments_count DESC, nc.modification_date) rn, nc.* FROM (SELECT n.news_id , n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date, COUNT(c.news_id) AS comments_count FROM news n LEFT JOIN comments c ON n.news_id=c.news_id GROUP BY n.news_id, n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date) nc LEFT JOIN (SELECT na.author_id, na.news_id, nt.tag_id FROM news_tags nt LEFT JOIN news_authors na ON na.news_id = nt.news_id) links ON links.news_id = nc.news_id ) res  WHERE res.rn  BETWEEN ? AND ?; 
//SELECT  res.news_id, res.title, res.short_text, res.full_text, res.creation_date, res.modification_date FROM ( SELECT row_number() OVER (ORDER BY nc.comments_count DESC, nc.modification_date) rn, nc.* FROM (SELECT n.news_id , n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date, COUNT(c.news_id) AS comments_count FROM news n LEFT JOIN comments c ON n.news_id=c.news_id GROUP BY n.news_id, n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date) nc LEFT JOIN (SELECT na.author_id, na.news_id, nt.tag_id FROM news_tags nt LEFT JOIN news_authors na ON na.news_id = nt.news_id) links ON links.news_id = nc.news_id WHERE links.tag_id IN (?,?,?,?)) res  WHERE res.rn  BETWEEN ? AND ?; 
//SELECT  res.news_id, res.title, res.short_text, res.full_text, res.creation_date, res.modification_date FROM ( SELECT row_number() OVER (ORDER BY nc.comments_count DESC, nc.modification_date) rn, nc.* FROM (SELECT n.news_id , n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date, COUNT(c.news_id) AS comments_count FROM news n LEFT JOIN comments c ON n.news_id=c.news_id GROUP BY n.news_id, n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date) nc LEFT JOIN (SELECT na.author_id, na.news_id, nt.tag_id FROM news_tags nt LEFT JOIN news_authors na ON na.news_id = nt.news_id) links ON links.news_id = nc.news_id WHERE links.author_id=? ) res  WHERE res.rn  BETWEEN ? AND ?; 
//SELECT  res.news_id, res.title, res.short_text, res.full_text, res.creation_date, res.modification_date FROM ( SELECT row_number() OVER (ORDER BY nc.comments_count DESC, nc.modification_date) rn, nc.* FROM (SELECT n.news_id , n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date, COUNT(c.news_id) AS comments_count FROM news n LEFT JOIN comments c ON n.news_id=c.news_id GROUP BY n.news_id, n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date) nc LEFT JOIN (SELECT na.author_id, na.news_id, nt.tag_id FROM news_tags nt LEFT JOIN news_authors na ON na.news_id = nt.news_id) links ON links.news_id = nc.news_id WHERE links.author_id=? AND links.tag_id IN (?,?,?,?)) res  WHERE res.rn  BETWEEN ? AND ?; 

}
