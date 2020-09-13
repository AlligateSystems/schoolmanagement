package com.as.controller.school1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school1.School1_AttendanceForm;
import com.as.service.school1.School1_AttendanceService;

@Controller
public class School1_AttendanceController {

	@Autowired
	School1_AttendanceService attendanceservice;

	@GetMapping("school1/attendance")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("school1/attendance");
		modelAndView.addObject("pageTitle", "Add Attendance");
		return modelAndView;
	}

	@PostMapping("school1/attendance/attendance")
	public @ResponseBody BaseResponse addAttendance(School1_AttendanceForm form) {
		return attendanceservice.addAttendance(form);
	}

	@PostMapping("school1/attendance/getAll")
	public @ResponseBody BaseResponse getAll() {
		return attendanceservice.getAttendance();
	}

	@PostMapping("school1/attendance/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return attendanceservice.deleteAttendance(registerNumber);
	}

}
