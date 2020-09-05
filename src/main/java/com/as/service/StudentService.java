package com.as.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.dao.StudentRepository;
import com.as.entity.StudentEntity;
import com.as.form.StudentForm;

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
