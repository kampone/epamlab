package com.epam.newsmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
@RequestMapping("/authors")
public class AuthorsController {
	
	@Autowired
	private ServiceManager service;
	
	@RequestMapping("/view")
	public String viewAuthors(Model model) throws ServiceException{
		model.addAttribute("authorList", service.getAllAuthors());
		return "author";
	}
	
	@RequestMapping("/add")
	public String addAuthor(RedirectAttributes redirectAttributes, @Valid Author author, BindingResult bindingResult) throws ServiceException{
		if (!bindingResult.hasErrors()) {
			service.addNewAuthor(author);
		}else{
		    redirectAttributes.addFlashAttribute("author", author);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.author", bindingResult);
		}
		return "redirect:/authors/view";
	}
	
	@RequestMapping("/update")
	public String updateAuthor(RedirectAttributes redirectAttributes, @Valid Author author, BindingResult bindingResult) throws ServiceException{
		if (!bindingResult.hasErrors()) {
			service.updateAuthor(author);
		}else{
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.author", bindingResult);
		}
		return "redirect:/authors/view";
	}
	
	@RequestMapping("/delete/{idAuthor}")
	public String deleteAuthor(@PathVariable("idAuthor") Long idAuthor) throws ServiceException{
		service.deleteAuthor(idAuthor);
		return "redirect:/authors/view";
	}
	
}
