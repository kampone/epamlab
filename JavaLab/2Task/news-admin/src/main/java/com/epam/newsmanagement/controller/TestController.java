package com.epam.newsmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.NewsVO;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

@Controller
public class TestController {

	@Autowired
	private ServiceManager service;
	@RequestMapping("/test")
	public String test( Model model) throws ServiceException {
		Author author = new Author();
		List<News> news = service.getNews(null, Integer.MAX_VALUE, Integer.MIN_VALUE);
		System.out.println(news);
		System.out.println(getListVO(news));
		model.addAttribute("message", service.getNewsVO(1L));
		return "profile";
		
	}
	
	private List<NewsVO> getListVO(List<News> list) throws ServiceException{
		List<NewsVO> newsList = new ArrayList<>();
		for (News news : list) {
			newsList.add(service.getNewsVO(news.getId()));
		}
		return newsList;
	}
}
