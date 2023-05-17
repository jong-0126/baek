package com.icia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("shopController")
public class ShopController {
	private static Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@RequestMapping(value="/shop/dogMain")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/shop/dogMain";
	
	}
	
	@RequestMapping(value="/shop/detail")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/shop/detail";
	
	}
}
