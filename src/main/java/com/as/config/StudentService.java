package com.as.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	
	@Autowired
	StudentRepository studentRepository;
	
	public BaseResponse addStudent(StudentForm form) {
	
	BaseResponse response = new BaseResponse();
	StudentEntity student = studentRepository.findByRegisternumber(form.getRegisternumber());
	if(student!=null) {
		response.setStatus(300);
		response.setMessage("Register Number already exits");
		return response;
	}
	student = new StudentEntity();
	BeanUtils.copyProperties(form,student);
	studentRepository.save(student);	
	response.setStatus(200);
	response.setMessage("Student Registered");
	System.out.println(response);
	return response;
	}
}
