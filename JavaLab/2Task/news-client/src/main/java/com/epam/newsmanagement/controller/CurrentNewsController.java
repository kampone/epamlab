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
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String getCurrentNews(HttpSession session, Model model, @PathVariable("index") Integer index)
			throws ServiceException {
		session.setAttribute("index", index);
		return "redirect:/current/see";
	}

	@RequestMapping("/next")
	public String getNextNews(HttpSession session, Model model) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		Integer index = (Integer) session.getAttribute("index");
		++index;
		List<NewsVO> newsVOList = service.getNewsVO(searchCriteria, index, index);
		if (!newsVOList.isEmpty()) {
			session.setAttribute("index", index);
			prepareNewsByIndex(session, model);
		} else {
			--index;
			model.addAttribute("errorMessage", "Nothing else");
			prepareNewsByIndex(session, model);
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
			prepareNewsByIndex(session, model);
		} else {
			++index;
			model.addAttribute("errorMessage", "Nothing else");
			prepareNewsByIndex(session, model);
		}
		return "current_news";
	}

	@RequestMapping(value="/add-comment", method=RequestMethod.POST)
	public String addComment(HttpSession session, Model model, @Valid Comment comment, BindingResult bindingResult)
			throws ServiceException {
		if (!bindingResult.hasErrors()) {
			service.createComment(comment);
		}
		prepareNewsByIndex(session, model);
		return "current_news";
	}
	
	@RequestMapping(value="/add-comment", method=RequestMethod.GET)
	public String ingore(HttpSession session, Model model) throws ServiceException{
		prepareNewsByIndex(session, model);
		return "current_news";
	}

	private void prepareNewsByIndex(HttpSession session, Model model) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		int index = (int) session.getAttribute("index");
		model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
	}

	@RequestMapping("/see")
	public String watchNews(HttpSession session, Model model) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		int index = (int) session.getAttribute("index");
		model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
		return "current_news";
	}
	
	

}
