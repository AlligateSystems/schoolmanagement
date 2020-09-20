package com.as.controller.school1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school1.School1_StudentForm;
import com.as.service.school1.School1_StudentService;

@Controller
public class School1_StudentController {

	@Autowired
	School1_StudentService studentService;

	@GetMapping("school1")
	public ModelAndView school1() {
		ModelAndView modelAndView = new ModelAndView("school1/school1");
		modelAndView.addObject("pageTitle", "Home");
		return modelAndView;
	}

	@GetMapping({ "school1/student_add", "school1/student_add/" })
	public ModelAndView student_add() {
		ModelAndView modelAndView = new ModelAndView("school1/student_add");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}

	@GetMapping({ "school1/student_view", "school1/student_view/" })
	public ModelAndView student_view() {
		ModelAndView modelAndView = new ModelAndView("school1/student_view");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}

	@PostMapping("school1/student")
	public @ResponseBody BaseResponse addStudent(School1_StudentForm form) {
		if (form.getId().isEmpty()) {
			return studentService.addStudent(form);
		} else {
			return studentService.updateStudent(form);
		}
	}

	@PostMapping("school1/student/getAll")
	public @ResponseBody BaseResponse getAll() {
		return studentService.getStudent();
	}

	@PostMapping("school1/student/delete/{id}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "id") int id) {
		return studentService.deleteStudent(id);
	}

}
