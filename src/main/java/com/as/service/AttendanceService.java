package com.as.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.AttendenceEntity;
import com.as.form.AttendanceForm;
import com.as.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	public BaseResponse addAttendance(AttendanceForm form) {
		BaseResponse response = new BaseResponse();

		List<AttendenceEntity> check = attendanceRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Already Attended");
			return response;
		}
		AttendenceEntity entity = new AttendenceEntity();

		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("June");
		entity.setPresent(form.getJunePresent());
		entity.setTotal(form.getJuneTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("July");
		entity.setPresent(form.getJulyPresent());
		entity.setTotal(form.getJulyTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("August");
		entity.setPresent(form.getAugustPresent());
		entity.setTotal(form.getAugustTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("September");
		entity.setPresent(form.getSeptemberPresent());
		entity.setTotal(form.getSeptemberTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("Octomer");
		entity.setPresent(form.getOctomerPresent());
		entity.setTotal(form.getOctomerTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("November");
		entity.setPresent(form.getNovemberPresent());
		entity.setTotal(form.getNovemberTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("December");
		entity.setPresent(form.getDecemberPresent());
		entity.setTotal(form.getDecemberTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("January");
		entity.setPresent(form.getJanuaryPresent());
		entity.setTotal(form.getJanuaryTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("February");
		entity.setPresent(form.getFebruaryPresent());
		entity.setTotal(form.getFebruaryTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("March");
		entity.setPresent(form.getMarchPresent());
		entity.setTotal(form.getMarchTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("April");
		entity.setPresent(form.getAprilPresent());
		entity.setTotal(form.getAprilTotal());
		attendanceRepository.save(entity);

		entity = new AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("May");
		entity.setPresent(form.getMayPresent());
		entity.setTotal(form.getMayTotal());
		entity = attendanceRepository.save(entity);

		if (entity.getRegisterNumber() != null) {
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Student Attended");
			return response;
		}

		return response;
	}

	public BaseResponse getAttendance() {
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Asstedace");
		response.setBody(attendanceRepository.findAll());

		return response;
	}

	public BaseResponse deleteAttendance(String registerNumber) {
		BaseResponse response = new BaseResponse();
		List<AttendenceEntity> list = attendanceRepository.findByRegisterNumber(registerNumber);
		attendanceRepository.deleteAll(list);
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Asstedace Deleted");

		return response;
	}

}
