package com.icia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("qnaController")
public class QnaController {
	private static Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@RequestMapping(value="/qna/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/qna/list";
	
	}
	
	@RequestMapping(value="/qna/write")
	public String write(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/qna/write";
	
	}
	
}
