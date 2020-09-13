package com.as.service.school3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.school3.School3_AttendenceEntity;
import com.as.form.school3.School3_AttendanceForm;
import com.as.repository.school3.School3_AttendanceRepository;

@Service
public class School3_AttendanceService {

	@Autowired
	School3_AttendanceRepository attendanceRepository;

	public BaseResponse addAttendance(School3_AttendanceForm form) {
		BaseResponse response = new BaseResponse();

		List<School3_AttendenceEntity> check = attendanceRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Already Attended");
			return response;
		}
		School3_AttendenceEntity entity = new School3_AttendenceEntity();

		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("June");
		entity.setPresent(form.getJunePresent());
		entity.setTotal(form.getJuneTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("July");
		entity.setPresent(form.getJulyPresent());
		entity.setTotal(form.getJulyTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("August");
		entity.setPresent(form.getAugustPresent());
		entity.setTotal(form.getAugustTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("September");
		entity.setPresent(form.getSeptemberPresent());
		entity.setTotal(form.getSeptemberTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("Octomer");
		entity.setPresent(form.getOctomerPresent());
		entity.setTotal(form.getOctomerTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("November");
		entity.setPresent(form.getNovemberPresent());
		entity.setTotal(form.getNovemberTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("December");
		entity.setPresent(form.getDecemberPresent());
		entity.setTotal(form.getDecemberTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("January");
		entity.setPresent(form.getJanuaryPresent());
		entity.setTotal(form.getJanuaryTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("February");
		entity.setPresent(form.getFebruaryPresent());
		entity.setTotal(form.getFebruaryTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("March");
		entity.setPresent(form.getMarchPresent());
		entity.setTotal(form.getMarchTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setMonth("April");
		entity.setPresent(form.getAprilPresent());
		entity.setTotal(form.getAprilTotal());
		attendanceRepository.save(entity);

		entity = new School3_AttendenceEntity();
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
		List<School3_AttendenceEntity> list = attendanceRepository.findAll();
		List<School3_AttendenceEntity> array = new ArrayList<>();
		for (School3_AttendenceEntity School3_AttendenceEntity : list) {
			if (!checkA(array, School3_AttendenceEntity)) {
				array.add(School3_AttendenceEntity);
			}
		}
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Asstedace");
		response.setBody(array);
		return response;
	}

	public boolean checkA(List<School3_AttendenceEntity> array, School3_AttendenceEntity entity) {
		for (School3_AttendenceEntity School3_AttendenceEntity : array) {
			if (School3_AttendenceEntity.getRegisterNumber().equals(entity.getRegisterNumber())) {
				return true;
			}
		}
		return false;
	}

	public BaseResponse deleteAttendance(String registerNumber) {
		BaseResponse response = new BaseResponse();
		List<School3_AttendenceEntity> list = attendanceRepository.findByRegisterNumber(registerNumber);
		attendanceRepository.deleteAll(list);
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Asstedace Deleted");

		return response;
	}

}
