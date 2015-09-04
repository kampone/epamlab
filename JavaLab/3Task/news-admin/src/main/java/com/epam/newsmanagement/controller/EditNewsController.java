package com.epam.newsmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
@RequestMapping("/edit_news")
public class EditNewsController {

	@Autowired
	private ServiceManager service;
	
	
	@ModelAttribute
	public News getNews() {
		return new News();
	}

	@RequestMapping("/view")
	public String createNews(Model model) throws ServiceException {
		model.addAttribute("authors", service.getAllAuthors());
		model.addAttribute("tags", service.getAllTags());
		return "edit_news";
	}

	@RequestMapping("/edit/{newsId}")
	public String editNews(RedirectAttributes redirectAttributes, @PathVariable("newsId") Long newsId)
			throws ServiceException {
		redirectAttributes.addFlashAttribute("newsPageVO", service.getSingleNews(newsId));
		return "redirect:/edit_news/view";
	}

	@RequestMapping("/save")
	public String saveNews(RedirectAttributes redirectAttributes, @Valid News newsPageVO,
			 BindingResult bindingResult) throws ServiceException{
		if (!bindingResult.hasErrors()) {
		/*	if (news.getNewsId() == null) {
				service.createNewsPageVO(newsPageVO);
			} else {
				service.updateNewsPageVO(newsPageVO);
			}*/
			return "redirect:/news/watch";
		} else {
			redirectAttributes.addFlashAttribute("newsPageVO", newsPageVO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsPageVO",
					bindingResult);
			return "redirect:/edit_news/view";
		}
	}
}
