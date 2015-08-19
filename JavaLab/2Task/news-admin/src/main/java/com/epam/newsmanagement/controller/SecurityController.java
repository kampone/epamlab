package com.epam.newsmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping("/")
	public String login(){
		return "redirect:/news/watch";
	}
	
	@RequestMapping("/login")
	public String insertLogin(){
		return "login";
	}
}
