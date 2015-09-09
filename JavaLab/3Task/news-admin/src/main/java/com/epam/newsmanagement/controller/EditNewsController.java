package com.epam.newsmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@RequestMapping("/view")
	public String createNews(Model model) throws ServiceException {
		model.addAttribute("authors", service.getAllAuthors());
		model.addAttribute("tags", service.getAllTags());
		return "edit_news";
	}

	@RequestMapping("/edit/{newsId}")
	public String editNews(RedirectAttributes redirectAttributes, @PathVariable("newsId") Long newsId)
			throws ServiceException {
		redirectAttributes.addFlashAttribute("news", service.getSingleNews(newsId));
		return "redirect:/edit_news/view";
	}

	@RequestMapping("/save")
	public String saveNews(RedirectAttributes redirectAttributes, @Valid News news, BindingResult bindingResult)
			throws ServiceException {
		if (!bindingResult.hasErrors()) {

			if (news.getNewsId() == null) {
				service.addNews(news);
			} else {
				service.updateNews(news);
			}

			return "redirect:/news/watch";
		} else {
			System.out.println(bindingResult.getAllErrors());
			redirectAttributes.addFlashAttribute("news", news);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.news", bindingResult);
			return "redirect:/edit_news/view";
		}
	}
}
