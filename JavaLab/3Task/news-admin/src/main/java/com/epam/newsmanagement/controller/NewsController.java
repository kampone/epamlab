	package com.epam.newsmanagement.controller;

import static com.epam.newsmanagement.util.NewsUtil.setPreparedParametres;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private ServiceManager service;


	@RequestMapping("/watch")
	public String getAllNews(HttpSession session, Model model, @RequestParam(value = "page", required=false) Integer page)
			throws ServiceException {
		setPreparedParametres(service, session, model, page);
		return "news";
	}

	@RequestMapping("/setSearchCriteria")
	public String setSearchCriteriaToSession(HttpSession session, SearchCriteria searchCriteria) throws ServiceException {
		session.setAttribute("searchCriteria", searchCriteria);
		return "redirect:/news/watch";	
	}

	@RequestMapping("/reset")
	public String resetSearchCriteria(HttpSession session) {
		session.setAttribute("searchCriteria", null);
		return "redirect:/news/watch";
	}

	@RequestMapping("/delete/{newsId}")
	public String changePage(@PathVariable("newsId") Long newsId) throws ServiceException {
		service.deleteNews(newsId);
		return "redirect:/news/watch";
	}
	
	@RequestMapping("/page/{page}")
	public String changePage(HttpSession session, Model model, @PathVariable("page") Integer page) throws ServiceException {
		setPreparedParametres(service, session, model, page);
		return "news";
	}

	
}
