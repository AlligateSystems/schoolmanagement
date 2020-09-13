package com.as.controller.school1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school1.School1_MarksForm;
import com.as.service.school1.School1_MarksService;

@Controller
public class School1_MarksController {

	@Autowired
	School1_MarksService marksService;

	@GetMapping("/school1/marks_a")
	public ModelAndView marksA() {
		ModelAndView modelAndView = new ModelAndView("school1/marksA");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@GetMapping("school1/marks_b")
	public ModelAndView marksB() {
		ModelAndView modelAndView = new ModelAndView("school1/marksB");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@GetMapping("school1/marks_view")
	public ModelAndView marks_view() {
		ModelAndView modelAndView = new ModelAndView("school1/marks_view");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@PostMapping("school1/marks/marks_a")
	public @ResponseBody BaseResponse addMarksA(School1_MarksForm form) {
		return marksService.addMarksA(form);
	}

	@PostMapping("school1/marks/marks_b")
	public @ResponseBody BaseResponse addMarksB(School1_MarksForm form) {
		return marksService.addMarksB(form);
	}

	@PostMapping("school1/marks/getAll")
	public @ResponseBody BaseResponse getAll() {
		return marksService.getMarks();
	}

	@PostMapping("school1/marks/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return marksService.deleteMarks(registerNumber);
	}
}