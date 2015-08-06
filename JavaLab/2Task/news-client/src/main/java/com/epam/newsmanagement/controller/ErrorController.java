package com.epam.newsmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class ErrorController extends RuntimeException {
	
	@RequestMapping("/error")
	public String error(Model model, HttpStatus httpStatus) {
		model.addAttribute("status", httpStatus);
		return "error";
	}

}
