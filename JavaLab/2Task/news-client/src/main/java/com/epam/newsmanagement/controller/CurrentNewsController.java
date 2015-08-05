package com.epam.newsmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.NewsVO;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
@RequestMapping("/current")
public class CurrentNewsController {

	@Autowired
	private ServiceManager service;

	@RequestMapping("/news/{index}")
	public String getCurrentNews(@PathVariable("index") Integer index, HttpSession session,
			Model model) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		session.setAttribute("index", index);
		
		model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
		return "current_news";
	}

	@RequestMapping("/next")
	public String getNextNews(HttpSession session, Model model) throws ServiceException {
		SearchCriteria searchCriteria=(SearchCriteria) session.getAttribute("searchCriteria");
		Integer index = (Integer) session.getAttribute("index");
		++index;
		List<NewsVO> newsVOList = service.getNewsVO(searchCriteria, index, index);
		if (!newsVOList.isEmpty()) {
			session.setAttribute("index", index);
			model.addAttribute("newsVO", newsVOList.get(0));
		} else {
			--index;
			session.setAttribute("index", index);
			model.addAttribute("errorMessage", "Nothing else");
			model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));

		}
		return "current_news";
	}

	@RequestMapping("/previous")
	public String getPreviousNews(HttpSession session, Model model) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		Integer index = (Integer) session.getAttribute("index");
		--index;
		List<NewsVO> newsVOList = service.getNewsVO(searchCriteria, index, index);
		if (!newsVOList.isEmpty()) {
			session.setAttribute("index", index);
			model.addAttribute("newsVO", newsVOList.get(0));
		} else {
			++index;
			session.setAttribute("index", index);
			model.addAttribute("errorMessage", "Nothing else");
			model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
		}
		return "current_news";
	}

	@RequestMapping("/add-comment")
	public String addComment(HttpSession session, Model model,@Valid Comment comment,BindingResult bindingResult) throws ServiceException {
		if(bindingResult.hasErrors()){
			model.addAttribute("newsVO", service.getNewsVO(comment.getNewsId()));
			return "current_news";
		}
		service.createComment(comment);
		model.addAttribute("newsVO", service.getNewsVO(comment.getNewsId()));
		return "current_news";
	}
}
