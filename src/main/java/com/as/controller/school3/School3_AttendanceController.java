package com.as.controller.school3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school3.School3_AttendanceForm;
import com.as.service.school3.School3_AttendanceService;

@Controller
public class School3_AttendanceController {

	@Autowired
	School3_AttendanceService attendanceservice;

	@GetMapping("school3/attendance")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("school3/attendance");
		modelAndView.addObject("pageTitle", "Add Attendance");
		return modelAndView;
	}

	@PostMapping("school3/attendance/attendance")
	public @ResponseBody BaseResponse addAttendance(School3_AttendanceForm form) {
		return attendanceservice.addAttendance(form);
	}

	@PostMapping("school3/attendance/getAll")
	public @ResponseBody BaseResponse getAll() {
		return attendanceservice.getAttendance();
	}

	@PostMapping("school3/attendance/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return attendanceservice.deleteAttendance(registerNumber);
	}

}
