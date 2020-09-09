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
@RequestMapping("marks")
public class MarksController {

	@Autowired
	MarksService marksService;

	@GetMapping("")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("marks");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@PostMapping("marks")
	public @ResponseBody BaseResponse addMarks(MarksForm form) {
		return marksService.addMarks(form);
	}

	@PostMapping("getAll")
	public @ResponseBody BaseResponse getAll() {
		return marksService.getMarks();
	}

	@PostMapping("/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return marksService.deleteMarks(registerNumber);
	}
}
