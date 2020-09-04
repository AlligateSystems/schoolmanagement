package com.as.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@GetMapping("student")
	public ModelAndView student() {
		ModelAndView modelAndView = new ModelAndView("student");
		modelAndView.addObject("pageTitle", "Add Student"); 
		return modelAndView;
	}
	
//	@PostMapping("student")
//	public BaseResponse addStudent(StudentForm form) {
//		
//		return modelAndView;
//	}
}
