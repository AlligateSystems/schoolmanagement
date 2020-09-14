package com.as.controller.school3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school3.School3_MarksForm;
import com.as.service.school3.School3_MarksService;

@Controller
public class School3_MarksController {

	@Autowired
	School3_MarksService marksService;

	@GetMapping("/school3/marks_a")
	public ModelAndView marksA() {
		ModelAndView modelAndView = new ModelAndView("school3/marksA");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}

	@GetMapping("school3/marks_b")
	public ModelAndView marksB() {
		ModelAndView modelAndView = new ModelAndView("school3/marksB");
		modelAndView.addObject("pageTitle", "Add Marks");
		return modelAndView;
	}
	
	@GetMapping("school3/marks_view")
	public ModelAndView marks_view() {
		ModelAndView modelAndView = new ModelAndView("school3/marks_view");
		modelAndView.addObject("pageTitle", "View/Print Marks");
		return modelAndView;
	}

	@PostMapping("school3/marks/marks_a")
	public @ResponseBody BaseResponse addMarksA(School3_MarksForm form) {
		return marksService.addMarksA(form);
	}

	@PostMapping("school3/marks/marks_b")
	public @ResponseBody BaseResponse addMarksB(School3_MarksForm form) {
		return marksService.addMarksB(form);
	}

	@PostMapping("school3/marks/getAll")
	public @ResponseBody BaseResponse getAll() {
		return marksService.getMarks();
	}

	@PostMapping("school3/marks/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return marksService.deleteMarks(registerNumber);
	}
}