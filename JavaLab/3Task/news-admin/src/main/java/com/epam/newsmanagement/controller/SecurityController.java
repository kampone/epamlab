package com.epam.newsmanagement.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping("/")
	public String login(){
		return "redirect:/news/watch";
	}
	
	@RequestMapping("/login")
	public String insertLogin(){
		return "login";
	}
	@RequestMapping("/login/authfailed")
	public String wrongLogin(Model model, Locale locale){
		String errorMessage = messageSource.getMessage("label.message.error.login_password", null, locale);
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}
}
