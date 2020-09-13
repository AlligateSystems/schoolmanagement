package com.as.service.school3;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.school3.School3_StudentEntity;
import com.as.form.school3.School3_StudentForm;
import com.as.repository.school3.School3_StudentRepository;

@Service
public class School3_StudentService {

	@Autowired
	School3_StudentRepository studentRepository;

	public BaseResponse addStudent(School3_StudentForm form) {
		BaseResponse response = new BaseResponse();
		School3_StudentEntity student = studentRepository.findByRegisterNumber(form.getRegisterNumber());
		if (student != null) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Already Exits");
			return response;
		}
		student = new School3_StudentEntity();
		BeanUtils.copyProperties(form, student);
		student.setFullName(form.getFirstName() + " " + form.getLastName());
		student.setCreatedDate(new Date());
		student.setEnable(true);
		student = studentRepository.save(student);
		if (student.getFirstName() != null) {
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Student Saved");
			return response;
		}
		return response;
	}

	public BaseResponse getStudent() {
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Saved");
		response.setBody(studentRepository.findAll());
		return response;
	}

	public BaseResponse deleteStudent(int id) {
		BaseResponse response = new BaseResponse();
		School3_StudentEntity entity = studentRepository.findById(id);
		if (entity == null) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Not Found");
			return response;
		} else {
			studentRepository.delete(entity);
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Student Deleted");
			return response;
		}
	}

	public BaseResponse updateStudent(School3_StudentForm form) {
		BaseResponse response = new BaseResponse();
		int id = Integer.parseInt(form.getId());
		School3_StudentEntity student = studentRepository.findById(id);
		if (student == null) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Not Found");
			return response;
		} else {
			BeanUtils.copyProperties(form, student);
			student.setId(id);
			student.setFullName(form.getFirstName() + " " + form.getLastName());
			student.setUpdatedDate(new Date());
			student.setEnable(true);
			student = studentRepository.save(student);
			if (student.getFirstName() != null) {
				response.setStatus(200);
				response.setType(ResponseType.SUCCESS);
				response.setMessage("Student Update");
				return response;
			}
		}
		return response;
	}
}
