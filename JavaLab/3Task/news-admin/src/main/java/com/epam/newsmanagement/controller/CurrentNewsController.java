package com.epam.newsmanagement.controller;

import static com.epam.newsmanagement.util.NewsUtil.findIndex;

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
import com.epam.newsmanagement.util.NewsUtil;

@Controller
@RequestMapping("/current")
public class CurrentNewsController {

	@Autowired
	private ServiceManager service;

	@RequestMapping("/news/{index}")
	public String getCurrentNews(HttpSession session, Model model, @PathVariable("index") Integer index)
			throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		index = NewsUtil.processIndex(service, searchCriteria, index, model);
		model.addAttribute("index", index);
		model.addAttribute("newsVO", service.getNewsVO(searchCriteria, index, index).get(0));
		return "current_news";
	}

	@RequestMapping("/delete_comment/{commentId}")
	public String deleteComment(HttpSession session,@PathVariable("commentId") Long commentId) throws ServiceException{
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		Comment comment = service.readComment(commentId);
		service.deleteComment(commentId);
		return "redirect:/current/news/" + findIndex(service, searchCriteria, comment.getNews().getNewsId());
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
		return "redirect:/current/news/" + findIndex(service, searchCriteria, comment.getNews().getNewsId());

	}
	
	

	

}
