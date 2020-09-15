package com.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("exception")
public class ExceptionController {

	@GetMapping("")
	public ModelAndView getBonafideCertificate() {
		ModelAndView modelAndView = new ModelAndView("exception");
		modelAndView.addObject("getExceptionMessage", "Student Not In This Class !");
		return modelAndView;
	}
}
