package com.epam.newsmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {
	
	@RequestMapping("/error")
	public String error(Model model, HttpStatus httpStatus) {
		model.addAttribute("status", httpStatus);
		return "error";
	}

}
