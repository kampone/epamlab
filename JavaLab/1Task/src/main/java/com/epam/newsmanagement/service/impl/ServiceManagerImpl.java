/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
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

	
	public ServiceManagerImpl() {
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
	public void addNews(News news,Long idAuthor, List<Long> idTagList) throws ServiceException {
		Long idNews = newsService.create(news);
		tagService.attachListTags(idNews, idTagList);
		authorService.attachAuthor(idNews, idAuthor);
		
	}

	@Override
	public void updateNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException {
		tagService.detachTags(news.getId());
		authorService.detachAuthor(news.getId());
		newsService.update(news);
		authorService.attachAuthor(news.getId(), idAuthor);
		tagService.attachListTags(news.getId(), idTagList);

	}

	@Override
	public void deleteNews(Long idNews) throws ServiceException {
		tagService.detachTags(idNews);
		authorService.detachAuthor(idNews);
		commentService.deleteCommentsByNewsId(idNews);
		newsService.delete(idNews);

	}


	

}
