package com.epam.spitter.controller;

import org.springframework.stereotype.Controller;

import com.epam.newsmanagement.service.impl.ServiceManagerImpl;


@Controller
public class HomeController {
	public static final int DEFAULT_SPITTLES_PER_PAGE = 25;
	private ServiceManagerImpl service;

	

}
