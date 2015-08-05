package com.epam.newsmanagement.controller;

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
	public static final int DEFAULT_PAGE = 1;
	public static final int NUMBER_OF_NEWS_ON_PAGE = 3;

	@Autowired
	private ServiceManager service;
	

	@RequestMapping("/all")
	public String addAttrbutes(HttpSession session, Model model, @RequestParam(value="page", required=false) Integer page) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		page = page == null ? 1 : page;
		int startIndex = (page-1) * NUMBER_OF_NEWS_ON_PAGE+1;
		int lastIndex = startIndex + NUMBER_OF_NEWS_ON_PAGE-1;
		int pages = (int) Math.ceil(service.getNumberOfNews(searchCriteria)/NUMBER_OF_NEWS_ON_PAGE);
		model.addAttribute("index", startIndex);
		model.addAttribute("authors",service.getAllAuthors());
		model.addAttribute("tags", service.getAllTags());
		model.addAttribute("newsVOList", service.getNewsVO(searchCriteria, startIndex, lastIndex));
		model.addAttribute("pages", pages);
		return "news";
	}
	
	@RequestMapping("/setSearchCriteria")
	public String setSearchCriteriaToSession(SearchCriteria searchCriteria, HttpSession session) throws ServiceException {
		session.setAttribute("searchCriteria", searchCriteria);
		return "redirect:/news/all";
	}
	
	@RequestMapping("/reset")
	public String resetSearchCriteria(HttpSession session){
		session.setAttribute("searchCriteria", null);
		return "redirect:/news/all";
	}
	
	@RequestMapping("/page/{page}")
	public String changePage(@PathVariable("page") Integer page){
		
		return "redirect:/news/all";
	}
}
