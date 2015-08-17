package com.epam.newsmanagement.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
@RequestMapping("/current")
public class CurrentNewsController {

	@Autowired
	private ServiceManager service;

	@RequestMapping("/news/{index}")
	public String getCurrentNews(HttpSession session, Model model, @PathVariable("index") Integer index)
			throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		index = processIndex(searchCriteria, index, model);
		model.addAttribute("index", index);
		prepareNewsByIndex(session, model, index);
		return "current_news";
	}

	@RequestMapping(value = "/add-comment")
	public String addComment(HttpSession session, Model model, @Valid Comment comment, BindingResult bindingResult)
			throws ServiceException {
		if (!bindingResult.hasErrors()) {
			service.createComment(comment);
		}
		prepareNewsById(comment.getNewsId(), model);
		return "current_news";
	}

	private void prepareNewsByIndex(HttpSession session, Model model, Integer index) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
	}

	private void prepareNewsById(Long newsId, Model model) throws ServiceException {
		model.addAttribute("newsVO", service.getNewsVO(newsId));
	}

	private int processIndex(SearchCriteria searchCriteria, int index, Model model) throws ServiceException {
		int number = service.getNumberOfNews(searchCriteria);
		if (index > number) {
			model.addAttribute("errorMessage", "Nothing else");
			index = number;
		} else {
			if (index < 1) {
				model.addAttribute("errorMessage", "Nothing else");
				index = 1;
			} 
		}
		return index;
	}

}
