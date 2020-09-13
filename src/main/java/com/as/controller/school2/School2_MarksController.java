package com.as.controller.school2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school2.School2_MarksForm;
import com.as.service.school2.School2_MarksService;

@Controller
public class School2_MarksController {

	@Autowired
	School2_MarksService marksService;

	@GetMapping("/school2/marks_a")
	public ModelAndView marksA() {
		ModelAndView modelAndView = new ModelAndView("school2/marksA");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@GetMapping("school2/marks_b")
	public ModelAndView marksB() {
		ModelAndView modelAndView = new ModelAndView("school2/marksB");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}
	
	@GetMapping("school2/marks_view")
	public ModelAndView marks_view() {
		ModelAndView modelAndView = new ModelAndView("school2/marks_view");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@PostMapping("school2/marks/marks_a")
	public @ResponseBody BaseResponse addMarksA(School2_MarksForm form) {
		return marksService.addMarksA(form);
	}

	@PostMapping("school2/marks/marks_b")
	public @ResponseBody BaseResponse addMarksB(School2_MarksForm form) {
		return marksService.addMarksB(form);
	}

	@PostMapping("school2/marks/getAll")
	public @ResponseBody BaseResponse getAll() {
		return marksService.getMarks();
	}

	@PostMapping("school2/marks/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return marksService.deleteMarks(registerNumber);
	}
}