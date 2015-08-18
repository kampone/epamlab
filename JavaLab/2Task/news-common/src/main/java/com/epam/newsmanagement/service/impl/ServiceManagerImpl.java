/**
 * 
 */
package com.epam.newsmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsVO;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.entity.Tag;
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
@Transactional(rollbackFor = ServiceException.class)
public class ServiceManagerImpl implements ServiceManager {
	private TagService tagService;
	private CommentService commentService;
	private AuthorService authorService;
	private NewsService newsService;

	public ServiceManagerImpl() {
	}

	/**
	 * @param tagService
	 *            the tagService to set
	 */
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	/**
	 * @param commentService
	 *            the commentService to set
	 */
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * @param authorService
	 *            the authorService to set
	 */
	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	/**
	 * @param newsService
	 *            the newsService to set
	 */
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#addNews(com.epam.newsmanagement.entity.News,
	 *      java.lang.Long, java.util.List)
	 */
	@Override
	public Long addNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException {
		Long idNews = newsService.create(news);
		tagService.attachListTagsToNews(idNews, idTagList);
		authorService.attachAuthorToNews(idNews, idAuthor);
		return idNews;

	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#updateNews(com.epam.newsmanagement.entity.News,
	 *      java.lang.Long, java.util.List)
	 */
	@Override
	public void updateNews(News news, Long idAuthor, List<Long> idTagList) throws ServiceException {
		tagService.detachTagsFromNews(news.getId());
		authorService.detachAuthorFromNews(news.getId());
		newsService.update(news);
		authorService.attachAuthorToNews(news.getId(), idAuthor);
		tagService.attachListTagsToNews(news.getId(), idTagList);

	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#deleteNews(java.lang.Long)
	 */
	@Override
	public void deleteNews(Long idNews) throws ServiceException {
		tagService.detachTagsFromNews(idNews);
		authorService.detachAuthorFromNews(idNews);
		commentService.deleteCommentsByNewsId(idNews);
		newsService.delete(idNews);

	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#getNews(com.epam.newsmanagement.entity.SearchCriteria,
	 *      int, int)
	 */
	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws ServiceException {
		return newsService.getNews(searchCriteria, startIndex, lastIndex);
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#getSingleNews(java.lang.Long)
	 */
	@Override
	public News getSingleNews(Long idNews) throws ServiceException {
		return newsService.read(idNews);
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#addNewAuthor(com.epam.newsmanagement.entity.Author)
	 */
	@Override
	public Long addNewAuthor(Author author) throws ServiceException {
		return authorService.create(author);
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#attachListTagsForNews(java.lang.Long,
	 *      java.util.List)
	 */
	@Override
	public void attachListTagsForNews(Long idNews, List<Long> idTagList) throws ServiceException {
		tagService.attachListTagsToNews(idNews, idTagList);
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#addCommentForNews(java.util.List)
	 */
	@Override
	public void addCommentForNews(List<Comment> commentList) throws ServiceException {
		commentService.addCommnetsForNews(commentList);
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#deleteCommentsByNewsId(java.lang.Long)
	 */
	@Override
	public void deleteCommentsByNewsId(Long idNews) throws ServiceException {
		commentService.deleteCommentsByNewsId(idNews);
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#getNewsVO(java.lang.Long)
	 */
	@Override
	public NewsVO getNewsVO(Long idNews) throws ServiceException {
		NewsVO newsVO = new NewsVO();
		newsVO.setNews(newsService.read(idNews));
		newsVO.setAuthor(authorService.getAuthorByNewsId(idNews));
		newsVO.setTagList(tagService.getNewsTags(idNews));
		newsVO.setCommentList(commentService.getCommentsByNewsId(idNews));
		return newsVO;
	}

	/**
	 * @see com.epam.newsmanagement.service.ServiceManager#getAllAuthors()
	 */
	@Override
	public List<Author> getAllAuthors() throws ServiceException {
		return authorService.getAllAuthors();
	}

	@Override
	public List<Tag> getAllTags() throws ServiceException {
		return tagService.getAllTags();
	}

	@Override
	public List<NewsVO> getNewsVO(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws ServiceException {
		List<NewsVO> newsVOList = new ArrayList<>();
		NewsVO newsVO = null;
		List<News> newsList = newsService.getNews(searchCriteria, startIndex, lastIndex);
		for (News news : newsList) {
			newsVO = new NewsVO();
			Long idNews = news.getId();
			newsVO.setNews(news);
			newsVO.setAuthor(authorService.getAuthorByNewsId(idNews));
			newsVO.setTagList(tagService.getNewsTags(idNews));
			newsVO.setCommentList(commentService.getCommentsByNewsId(idNews));
			newsVOList.add(newsVO);
		}
		return newsVOList;
	}

	@Override
	public int getNumberOfNews(SearchCriteria searchCriteria) throws ServiceException {
		return newsService.getNewsNumber(searchCriteria);
	}

	@Override
	public Long createComment(Comment comment) throws ServiceException {
		return commentService.create(comment);
	}

	@Override
	public int findIndex(SearchCriteria searchCriteria, Long newsId) throws ServiceException {
		return newsService.findIndex(searchCriteria, newsId);
	}

	@Override
	public void deleteComment(Long commentId) throws ServiceException {
		commentService.delete(commentId);
	}

	@Override
	public Comment readComment(Long commentId) throws ServiceException {
		return commentService.read(commentId);
	}

	@Override
	public void addNewTag(Tag tag) throws ServiceException {
		tagService.create(tag);
	}

	@Override
	public void updateTag(Tag tag) throws ServiceException {
		tagService.update(tag);
	}

	@Override
	public void updateAuthor(Author author) throws ServiceException {
		authorService.update(author);
	}

	@Override
	public void deleteAuthor(Long idAuthor) throws ServiceException {
		authorService.delete(idAuthor);
	}

	@Override
	public void deleteTag(Long idTag) throws ServiceException {
		tagService.detachTag(idTag);
		tagService.delete(idTag);
	}


}
