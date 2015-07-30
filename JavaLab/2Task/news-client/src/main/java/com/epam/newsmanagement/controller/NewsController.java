package com.epam.newsmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
public class NewsController {
	public static final int DEFAULT_PAGE = 1;
	public static final int NUMBER_OF_NEWS_ON_PAGE = 3;

	@Autowired
	private ServiceManager service;
	

	@RequestMapping("/news")
	public String addAttrbutes(HttpSession session, Model model, @RequestParam(value="page", required=false) Integer page) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		model.addAttribute("authors",service.getAllAuthors());
		model.addAttribute("tags", service.getAllTags());
		page = page == null ? 1 : page;
		int startIndex = (page-1) * NUMBER_OF_NEWS_ON_PAGE+1;
		int lastIndex = startIndex + NUMBER_OF_NEWS_ON_PAGE-1; 
		model.addAttribute("newsList", service.getNews(searchCriteria, startIndex, lastIndex));
		return "news";
	}
	
	@RequestMapping("/setSearchCriteria")
	public String setSearchCriteriaToSession(SearchCriteria searchCriteria, HttpSession session) throws ServiceException {
		session.setAttribute("searchCriteria", searchCriteria);
		return "redirect:/news";
	}
	
	
}
