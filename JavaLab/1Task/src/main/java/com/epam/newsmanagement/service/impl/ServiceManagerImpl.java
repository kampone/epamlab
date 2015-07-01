/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Tag;
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
@Transactional(rollbackFor = Exception.class)
public class ServiceManagerImpl implements ServiceManager {
	private TagService tagService;
	private CommentService commentService;
	private AuthorService authorService;
	private NewsService newsService;

	/**
	 * 
	 */
	public ServiceManagerImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the tagService
	 */
	public TagService getTagService() {
		return tagService;
	}

	/**
	 * @param tagService
	 *            the tagService to set
	 */
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	/**
	 * @return the commentService
	 */
	public CommentService getCommentService() {
		return commentService;
	}

	/**
	 * @param commentService
	 *            the commentService to set
	 */
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * @return the authorService
	 */
	public AuthorService getAuthorService() {
		return authorService;
	}

	/**
	 * @param authorService
	 *            the authorService to set
	 */
	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	/**
	 * @return the newsService
	 */
	public NewsService getNewsService() {
		return newsService;
	}

	/**
	 * @param newsService
	 *            the newsService to set
	 */
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addNews(News news, List<Tag> tagList) throws ServiceException {
		long idNews = newsService.create(news);
		List<Long> idTagList = new ArrayList<Long>();
		for (Tag tag : tagList) {
			idTagList.add(tag.getId());
		}
		tagService.attachListTags(idNews, idTagList);

	}

	@Override
	public void updateNews(News news, List<Tag> tagList,
			List<Comment> commentList) throws ServiceException {
		

	}

	@Override
	public void deleteNews(Long idNews) throws ServiceException {
		tagService.detachTags(idNews);
		authorService.detachAuthors(idNews);
		commentService.deleteCommentsByNewsId(idNews);
		newsService.delete(idNews);

	}

	// Should be deleted
	@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
	public void testMethod() throws ServiceException, DAOException {
		Tag tag = new Tag();
		tag.setName("blank");
		long idTag = tagService.create(tag);
		Author author = new Author();
		author.setName("Vasja");
		long idAuthor = authorService.create(author);
		if (true)
			throw new DAOException("Test Exception");
		idAuthor = authorService.create(author);

	}

}
