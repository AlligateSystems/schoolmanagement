package com.as.controller.school2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.as.config.BaseResponse;
import com.as.form.school2.School2_AttendanceForm;
import com.as.service.school2.School2_AttendanceService;

@Controller
public class School2_AttendanceController {

	@Autowired
	School2_AttendanceService attendanceservice;

	@GetMapping("school2/attendance")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("school2/attendance");
		modelAndView.addObject("pageTitle", "Add Attendance");
		return modelAndView;
	}

	@PostMapping("school2/attendance/attendance")
	public @ResponseBody BaseResponse addAttendance(School2_AttendanceForm form) {
		return attendanceservice.addAttendance(form);
	}

	@PostMapping("school2/attendance/getAll")
	public @ResponseBody BaseResponse getAll() {
		return attendanceservice.getAttendance();
	}

	@PostMapping("school2/attendance/delete/{registerNumber}")
	public @ResponseBody BaseResponse delete(@PathVariable(value = "registerNumber") String registerNumber) {
		return attendanceservice.deleteAttendance(registerNumber);
	}

}
