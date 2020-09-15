package com.as.controller.school3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school3.School3_StudentForm;
import com.as.service.school3.School3_StudentService;

@Controller
public class School3_StudentController {

	@Autowired
	School3_StudentService studentService;

	@GetMapping("school3")
	public ModelAndView school3() {
		ModelAndView modelAndView = new ModelAndView("school3/school3");
		modelAndView.addObject("pageTitle", "Home"); 
		return modelAndView;
	}
	
	@GetMapping({"school3/student_add","school3/student_add/"})
	public ModelAndView student_add() {
		ModelAndView modelAndView = new ModelAndView("school3/student_add");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}
	@GetMapping({"school3/student_view","school3/student_view/"})
	public ModelAndView student_view() {
		ModelAndView modelAndView = new ModelAndView("school3/student_view");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}

	@PostMapping("school3/student")
	public @ResponseBody BaseResponse addStudent(School3_StudentForm form) {
		if (form.getId().isEmpty()) {
			return studentService.addStudent(form);
		} else {
			return studentService.updateStudent(form);
		}
	}

	@PostMapping("school3/student/getAll")
	public @ResponseBody BaseResponse getAll() {
		return studentService.getStudent();
	}

	@PostMapping("school3/student/delete/{id}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "id") int id) {
		System.out.println(id);
		return studentService.deleteStudent(id);
	}

}
