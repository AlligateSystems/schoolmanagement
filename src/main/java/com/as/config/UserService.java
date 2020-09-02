package com.as.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public BaseResponse signUp(UserForm userForm) {
		BaseResponse response = new BaseResponse();
		UserEntity user = repository.findByUsername(userForm.getUsername());
		if (user != null) {
			response.setStatus(300);
			response.setMessage("User Already Exist");
			return response;
		}
		user = new UserEntity();
		BeanUtils.copyProperties(userForm, user);
		user.setPassword(encoder.encode(userForm.getPassword()));
		repository.save(user);
		response.setStatus(200);
		response.setMessage("User Registered");
		return response;
	}

	public BaseResponse login(UserForm userForm) {
		BaseResponse response = new BaseResponse();
		UserEntity user = repository.findByUsername(userForm.getUsername());
		if (user == null) {
			response.setStatus(500);
			response.setMessage("User Does not Exist");
			return response;
		}
		if (!user.getPassword().equals(userForm.getPassword())) {
			response.setStatus(400);
			response.setMessage("Password Mismatch");
			return response;
		}
		response.setStatus(200);
		response.setMessage("Login Success");
		return response;
	}

}
