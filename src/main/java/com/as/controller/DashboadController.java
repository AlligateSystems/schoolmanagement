package com.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboadController {

	
	@GetMapping("school1")
	public ModelAndView school1() {
		ModelAndView modelAndView = new ModelAndView("school1");
		modelAndView.addObject("pageTitle", "Home"); 
		return modelAndView;
	}
	@GetMapping("school2")
	public ModelAndView school2() {
		ModelAndView modelAndView = new ModelAndView("school2");
		modelAndView.addObject("pageTitle", "Home"); 
		return modelAndView;
	}
	@GetMapping("school3")
	public ModelAndView school3() {
		ModelAndView modelAndView = new ModelAndView("school3");
		modelAndView.addObject("pageTitle", "Home"); 
		return modelAndView;
	}
	
}
