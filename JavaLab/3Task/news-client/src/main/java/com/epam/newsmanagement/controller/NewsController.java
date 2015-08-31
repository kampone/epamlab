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

	@RequestMapping("/watch")
	public String getAllNews(HttpSession session, Model model, @RequestParam(value = "page", required=false) Integer page)
			throws ServiceException {
		setParametres(session, model, page);
		return "news";
	}

	@RequestMapping("/setSearchCriteria")
	public String setSearchCriteriaToSession(HttpSession session, SearchCriteria searchCriteria )			throws ServiceException {
		session.setAttribute("searchCriteria", searchCriteria);
		return "redirect:/news/watch";
	}

	@RequestMapping("/reset")
	public String resetSearchCriteria(HttpSession session) {
		session.setAttribute("searchCriteria", null);
		return "redirect:/news/watch";
	}

	@RequestMapping("/page/{page}")
	public String changePage(HttpSession session, Model model, @PathVariable("page") Integer page) throws ServiceException {
		setParametres(session, model, page);
		return "news";
	}

	private void setParametres(HttpSession session, Model model, Integer page) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		page = page == null ? 1 : page;
		int startIndex = (page - 1) * NUMBER_OF_NEWS_ON_PAGE + 1;
		int lastIndex = startIndex + NUMBER_OF_NEWS_ON_PAGE - 1;
		int numberOfNews = service.getNumberOfNews(searchCriteria);
		int pages = Double.valueOf(Math.ceil((double)numberOfNews / NUMBER_OF_NEWS_ON_PAGE)).intValue();
		model.addAttribute("numberOfNews", numberOfNews);
		model.addAttribute("index", startIndex);
		model.addAttribute("authors", service.getAllAuthors());
		model.addAttribute("tags", service.getAllTags());
		model.addAttribute("newsVOList", service.getNewsVO(searchCriteria, startIndex, lastIndex));
		model.addAttribute("pages", pages);
	}
}
