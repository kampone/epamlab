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

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
@RequestMapping("/tags")
public class TagController {
	
	@Autowired
	private ServiceManager service;
	
	@ModelAttribute
	public Tag addTag(){
		return new Tag();
	}
	
	@RequestMapping("/view")
	public String viewAuthors(Model model) throws ServiceException{
		model.addAttribute("tagList", service.getAllTags());
		return "tag";
	}
	
	@RequestMapping("/add")
	public String addAuthor(RedirectAttributes redirectAttributes, @Valid Tag tag, BindingResult bindingResult) throws ServiceException{
		if (!bindingResult.hasErrors()) {
			service.addNewTag(tag);
		}else{
		    redirectAttributes.addFlashAttribute("tag", tag);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tag", bindingResult);
		}
		return "redirect:/tags/view";
	}
	

	@RequestMapping("/update")
	public String updateTag(RedirectAttributes redirectAttributes, @Valid Tag tag, BindingResult bindingResult) throws ServiceException{
		if (!bindingResult.hasErrors()) {
			service.updateTag(tag);
		}else{
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tag", bindingResult);
		}
		return "redirect:/tags/view";
	}
	
	@RequestMapping("/delete/{idTag}")
	public String deleteTag(@PathVariable("idTag") Long idTag) throws ServiceException{
		service.deleteTag(idTag);
		return "redirect:/tags/view";
	}
}
