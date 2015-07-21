package com.epam.newsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
public class TestController {

	@Autowired
	private ServiceManager service;

	@RequestMapping("/test")
	public String test( Model model) throws ServiceException {
		Author author = new Author();
		author.setName("VASYA!");
		model.addAttribute("message", service.addNewAuthor(author));
		return "profile";
		
	}
	
	
}
