package com.as.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@GetMapping("")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		modelAndView.addObject("pageTitle", "Home"); 
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
