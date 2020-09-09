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
import com.as.form.AttendanceForm;
import com.as.service.AttendanceService;

@Controller
@RequestMapping("attendance")
public class AttendanceController {

	@Autowired
	AttendanceService attendanceservice;

	@GetMapping("")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("attendance");
		modelAndView.addObject("pageTitle", "Add Attendance");
		return modelAndView;
	}

	@PostMapping("attendance")
	public @ResponseBody BaseResponse addAttendance(AttendanceForm form) {
		return attendanceservice.addAttendance(form);
	}

	@PostMapping("getAll")
	public @ResponseBody BaseResponse getAll() {
		return attendanceservice.getAttendance();
	}

	@PostMapping("/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return attendanceservice.deleteAttendance(registerNumber);
	}

}
