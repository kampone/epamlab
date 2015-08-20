package com.epam.newsmanagement.util;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;

import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.ServiceException;
import com.epam.newsmanagement.service.ServiceManager;

public class NewsUtil {
	public static final int DEFAULT_PAGE = 1;
	public static final int NUMBER_OF_NEWS_ON_PAGE = 3;
	private static final String ENGLIS_PATTERN = "MMM dd, yyyy";
	private static final String RUSSIAN_PATTERN = "dd.MM.yyyy"; 
	
	
	
	public static int processIndex(ServiceManager service, SearchCriteria searchCriteria, int index, Model model) throws ServiceException {
		int number = service.getNumberOfNews(searchCriteria);
		if (index > number) {
			model.addAttribute("errorMessage", "Nothing else");
			index = number;
		} else {
			if (index < 1) {
				model.addAttribute("errorMessage", "Nothing else");
				index = 1;
			}
		}
		return index;
	}
	
	public static int findIndex(ServiceManager service, SearchCriteria searchCriteria, Long newsId) throws ServiceException {
		return service.findIndex(searchCriteria, newsId);
	}
	
	public static void setPreparedParametres(ServiceManager service, HttpSession session, Model model, Integer page) throws ServiceException {
		SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute("searchCriteria");
		page = page == null ? 1 : page;
		int startIndex = (page - 1) * NUMBER_OF_NEWS_ON_PAGE + 1;
		int lastIndex = startIndex + NUMBER_OF_NEWS_ON_PAGE - 1;
		int numberOfNews = service.getNumberOfNews(searchCriteria);
		int pages = Double.valueOf(Math.ceil((double)numberOfNews / NUMBER_OF_NEWS_ON_PAGE)).intValue();
		model.addAttribute("numberOfNews", numberOfNews);
		model.addAttribute("index", startIndex);
		model.addAttribute("authors", service.getAllAuthors());
		model.addAttribute("tags", service.getAllTags());
		model.addAttribute("newsVOList", service.getNewsVO(searchCriteria, startIndex, lastIndex));
		model.addAttribute("pages", pages);
	}

	public static String getPattern(MessageSource messageSource, Locale locale) {
		return messageSource.getMessage("label.date.pattern", null, locale);
	}


	
	public static String getPattern(Locale locale){
		if ("en".equals(locale.toString())) {
			return ENGLIS_PATTERN;
		}else{
			if ("ru".equals(locale.toString())) {
				return RUSSIAN_PATTERN;
			}
		}
		return null;
	}

	
}
