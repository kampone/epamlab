package com.epam.newsmanagement.main;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.CommentService;
import com.epam.newsmanagement.service.TagService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"SpringContext.xml");
		DataSource pool = (DataSource) context.getBean("dataSource");
		TagService tagService = (TagService) context.getBean("tagService");
		CommentService commentService = (CommentService) context.getBean("commentService");
		
		Tag tag = new Tag();
		tag.setName("culture");
		
//		Comment comment = new Comment();
//		comment.setText("Nice comment!");
//		comment.setCreationDate(Timestamp.valueOf("2012-12-31 12:32:10"));
//		comment.setIdNews(1L);
		
		
		try {
			tagService.delete(8L);
/*			long id = tagService.create(tag);
			System.out.println(id);
			Tag tag2 = tagService.read(id);
			System.out.println(tag2.getId());
			System.out.println(tag2.getName());
			
*/			/*Comment comment2 = commentService.read(id);
			System.out.println(comment2.getText());
			System.out.println(comment2.getCreationDate());*/
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			pool.getConnection();
		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}
