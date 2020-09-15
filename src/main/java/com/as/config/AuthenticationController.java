package com.as.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.as.repository.school1.School1_StudentRepository;
import com.as.repository.school2.School2_StudentRepository;
import com.as.repository.school3.School3_StudentRepository;

@RestController
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@Autowired
	School1_StudentRepository sc1;
	@Autowired
	School3_StudentRepository sc3;
	@Autowired
	School2_StudentRepository sc2;
	
	@GetMapping("")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		modelAndView.addObject("pageTitle", "Home");
		modelAndView.addObject("school1", sc1.count());
		modelAndView.addObject("school2", sc2.count());
		modelAndView.addObject("school3", sc3.count());
		return modelAndView;
	}
	
	@GetMapping("register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}

	@PostMapping("signup")
	public BaseResponse signUp(UserForm userForm) {
		return userService.signUp(userForm);
	}

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@PostMapping("login")
	public BaseResponse login(UserForm userForm) {
		return userService.login(userForm);
	}
	
}
