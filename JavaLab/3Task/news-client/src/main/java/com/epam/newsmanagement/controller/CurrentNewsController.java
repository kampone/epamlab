package com.epam.newsmanagement.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
		return "current_news";
	}

	@RequestMapping(value = "/add-comment")
	public String addComment(HttpSession session,Model model,RedirectAttributes redirectAttributes,@Valid Comment comment, BindingResult bindingResult)
			throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		if (!bindingResult.hasErrors()) {
			service.createComment(comment);
			redirectAttributes.addFlashAttribute("comment", new Comment());
		} else {
			redirectAttributes.addFlashAttribute("comment", comment);
		    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.comment", bindingResult);
		}
		return "redirect:/current/news/" + findIndex(searchCriteria, comment.getNews().getNewsId());

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

	private int findIndex(SearchCriteria searchCriteria, Long newsId) throws ServiceException {
		return service.findIndex(	searchCriteria, newsId);
	}

}
