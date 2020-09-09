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
import com.as.form.StudentForm;
import com.as.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("")
	public ModelAndView student() {
		ModelAndView modelAndView = new ModelAndView("student");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}

	@PostMapping("student")
	public @ResponseBody BaseResponse addStudent(StudentForm form) {
		if (form.getId().isEmpty()) {
			return studentService.addStudent(form);
		} else {
			return studentService.updateStudent(form);
		}
	}

	@PostMapping("getAll")
	public @ResponseBody BaseResponse getAll() {
		return studentService.getStudent();
	}

	@PostMapping("/delete/{id}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "id") int id) {
		System.out.println(id);
		return studentService.deleteStudent(id);
	}

}
