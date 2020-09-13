package com.as.controller.school2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school2.School2_StudentForm;
import com.as.service.school2.School2_StudentService;

@Controller
public class School2_StudentController {

	@Autowired
	School2_StudentService studentService;

	@GetMapping("school2")
	public ModelAndView school2() {
		ModelAndView modelAndView = new ModelAndView("school2/school2");
		modelAndView.addObject("pageTitle", "Home"); 
		return modelAndView;
	}
	
	@GetMapping({"school2/student_add","school2/student_add/"})
	public ModelAndView student_add() {
		ModelAndView modelAndView = new ModelAndView("school2/student_add");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}
	@GetMapping({"school2/student_view","school2/student_view/"})
	public ModelAndView student_view() {
		ModelAndView modelAndView = new ModelAndView("school2/student_view");
		modelAndView.addObject("pageTitle", "Add New Students");
		return modelAndView;
	}

	@PostMapping("school2/student")
	public @ResponseBody BaseResponse addStudent(School2_StudentForm form) {
		if (form.getId().isEmpty()) {
			return studentService.addStudent(form);
		} else {
			return studentService.updateStudent(form);
		}
	}

	@PostMapping("school2/student/getAll")
	public @ResponseBody BaseResponse getAll() {
		return studentService.getStudent();
	}

	@PostMapping("school2/student/delete/{id}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "id") int id) {
		System.out.println(id);
		return studentService.deleteStudent(id);
	}

}
