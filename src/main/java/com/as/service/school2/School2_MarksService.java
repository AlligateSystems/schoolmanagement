package com.as.service.school2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.school2.School2_MarksEntity;
import com.as.form.school2.School2_MarksForm;
import com.as.repository.school2.School2_MarksRepository;

@Service
public class School2_MarksService {

	@Autowired
	School2_MarksRepository marksRepository;

	public BaseResponse addMarksA(School2_MarksForm form) {
		BaseResponse response = new BaseResponse();

		List<School2_MarksEntity> check = marksRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Marks Already");
			return response;
		}
		School2_MarksEntity entity = new School2_MarksEntity();

		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("qualitySkills");
		entity.setGrade(form.getQualitySkillsGrade());
		entity.setNote(form.getQualitySkillsNote());
		entity = marksRepository.save(entity);

		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("generalknowledge");
		entity.setGrade(form.getGeneralknowledgeGrade());
		entity.setNote(form.getGeneralknowledgeNote());
		entity = marksRepository.save(entity);

		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("language");
		entity.setGrade(form.getLanguageGrade());
		entity.setNote(form.getLanguageNote());
		entity = marksRepository.save(entity);

		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("mathematics");
		entity.setGrade(form.getMathematicsGrade());
		entity.setNote(form.getMathematicsNote());
		entity = marksRepository.save(entity);

		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("environmentScience");
		entity.setGrade(form.getEnvironmentScienceGrade());
		entity.setNote(form.getEnvironmentScienceNote());
		entity = marksRepository.save(entity);

		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("drawing");
		entity.setGrade(form.getDrawingGrade());
		entity.setNote(form.getDrawingNote());
		entity = marksRepository.save(entity);

		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("physicalEducation");
		entity.setGrade(form.getPhysicalEducationGrade());
		entity.setNote(form.getPhysicalEducationNote());
		entity = marksRepository.save(entity);
		
		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("music");
		entity.setGrade(form.getMusicGrade());
		entity.setNote(form.getMusicNote());
		entity = marksRepository.save(entity);
		
		entity = new School2_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("total");
		entity.setGrade(form.getTotalGrade());
		entity.setNote(form.getTotalNote());
		entity = marksRepository.save(entity);

		if (entity.getRegisterNumber() != null) {
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Student Marks Added");
			return response;
		}
		return response;
	}


	public BaseResponse getMarks() {
		BaseResponse response = new BaseResponse();
		List<School2_MarksEntity> list = marksRepository.findAll();
		List<School2_MarksEntity> array = new ArrayList<>();
		for (School2_MarksEntity School2_MarksEntity : list) {
			if (!checkA(array, School2_MarksEntity)) {
				array.add(School2_MarksEntity);
			}
		}
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Marks");
		response.setBody(array);
		return response;
	}

	public boolean checkA(List<School2_MarksEntity> array, School2_MarksEntity entity) {
		for (School2_MarksEntity School2_MarksEntity : array) {
			if (School2_MarksEntity.getRegisterNumber().equals(entity.getRegisterNumber())) {
				return true;
			}
		}
		return false;
	}

	public BaseResponse deleteMarks(String registerNumber) {
		BaseResponse response = new BaseResponse();
		List<School2_MarksEntity> list = marksRepository.findByRegisterNumber(registerNumber);
		marksRepository.deleteAll(list);
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Marks Deleted");

		return response;
	}

}
