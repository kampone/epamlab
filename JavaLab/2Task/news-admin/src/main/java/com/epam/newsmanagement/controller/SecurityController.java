package com.epam.newsmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping("/login")
	public String login(){
		System.out.println("Hello, world!");
		return "login";
	}
}
