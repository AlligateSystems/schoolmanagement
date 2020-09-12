package com.as.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.MarksForm;
import com.as.service.MarksService;

@Controller
@RequestMapping("")
public class MarksController {

	@Autowired
	MarksService marksService;

	@GetMapping("marks_a")
	public ModelAndView marksA() {
		ModelAndView modelAndView = new ModelAndView("marksA");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}
	
	@GetMapping("marks_b")
	public ModelAndView marksB() {
		ModelAndView modelAndView = new ModelAndView("marksB");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@PostMapping("marks/marks_a")
	public @ResponseBody BaseResponse addMarksA(MarksForm form) {
		return marksService.addMarksA(form);
	}
	
	@PostMapping("marks/marks_b")
	public @ResponseBody BaseResponse addMarksB(MarksForm form) {
		return marksService.addMarksB(form);
	}

	@PostMapping("marks/getAll")
	public @ResponseBody BaseResponse getAll() {
		return marksService.getMarks();
	}

	@PostMapping("marks/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return marksService.deleteMarks(registerNumber);
	}
}
