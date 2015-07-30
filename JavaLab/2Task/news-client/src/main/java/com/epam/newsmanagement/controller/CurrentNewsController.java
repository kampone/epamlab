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
public class CurrentNewsController {
	
	@Autowired
	private ServiceManager service;
	
	@RequestMapping("/current_news")
	public String setSearchCriteriaToSession(@RequestParam(value="newsId", required=false)Long newsId, Model model) throws ServiceException {
		model.addAttribute("news",service.getNewsVO(newsId));
		return "current_news";
	}
}
