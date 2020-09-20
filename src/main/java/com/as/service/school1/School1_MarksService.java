package com.as.service.school1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.school1.School1_MarksEntity;
import com.as.entity.school1.School1_StudentEntity;
import com.as.form.school1.School1_MarksForm;
import com.as.repository.school1.School1_MarksRepository;
import com.as.repository.school1.School1_StudentRepository;

@Service
public class School1_MarksService {

	@Autowired
	School1_MarksRepository marksRepository;
	@Autowired
	School1_StudentRepository studentRepository;

	public BaseResponse addMarksA(School1_MarksForm form) {
		BaseResponse response = new BaseResponse();

		List<School1_MarksEntity> check = marksRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Marks Already");
			return response;
		}
		School1_MarksEntity entity = new School1_MarksEntity();

		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Gujarati");
		entity.setTotal(form.getTotalGujarati());
		entity.setObtainedMarks(form.getObtainedMarksGujarati());
		entity.setObtainedGrade(form.getObtainedGradeGujarati());
		entity.setNote(form.getNoteGujarati());
		entity = marksRepository.save(entity);

		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Mathematics");
		entity.setTotal(form.getTotalMathematics());
		entity.setObtainedMarks(form.getObtainedMarksMathematics());
		entity.setObtainedGrade(form.getObtainedGradeMathematics());
		entity.setNote(form.getNoteMathematics());
		entity = marksRepository.save(entity);

		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("ScienceTechnology");
		entity.setTotal(form.getTotalScienceTechnology());
		entity.setObtainedMarks(form.getObtainedMarksScienceTechnology());
		entity.setObtainedGrade(form.getObtainedGradeScienceTechnology());
		entity.setNote(form.getNoteScienceTechnology());
		entity = marksRepository.save(entity);

		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("SocialScience");
		entity.setTotal(form.getTotalSocialScience());
		entity.setObtainedMarks(form.getObtainedMarksSocialScience());
		entity.setObtainedGrade(form.getObtainedGradeSocialScience());
		entity.setNote(form.getNoteSocialScience());
		entity = marksRepository.save(entity);

		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Hindi");
		entity.setTotal(form.getTotalHindi());
		entity.setObtainedMarks(form.getObtainedMarksHindi());
		entity.setObtainedGrade(form.getObtainedGradeHindi());
		entity.setNote(form.getNoteHindi());
		entity = marksRepository.save(entity);

		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("English");
		entity.setTotal(form.getTotalEnglish());
		entity.setObtainedMarks(form.getObtainedMarksEnglish());
		entity.setObtainedGrade(form.getObtainedGradeEnglish());
		entity.setNote(form.getNoteEnglish());
		entity = marksRepository.save(entity);

		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Sanskrit");
		entity.setTotal(form.getTotalSanskrit());
		entity.setObtainedMarks(form.getObtainedMarksSanskrit());
		entity.setObtainedGrade(form.getObtainedGradeSanskrit());
		entity.setNote(form.getNoteSanskrit());
		entity = marksRepository.save(entity);
		
		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Industry");
		entity.setTotal(form.getTotalIndustry());
		entity.setObtainedMarks(form.getObtainedMarksIndustry());
		entity.setObtainedGrade(form.getObtainedGradeIndustry());
		entity.setNote(form.getNoteIndustry());
		entity = marksRepository.save(entity);
		
		
		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Computer");
		entity.setTotal(form.getTotalComputer());
		entity.setObtainedMarks(form.getObtainedMarksComputer());
		entity.setObtainedGrade(form.getObtainedGradeComputer());
		entity.setNote(form.getNoteComputer());
		entity = marksRepository.save(entity);
		
		
		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Picture");
		entity.setTotal(form.getTotalPicture());
		entity.setObtainedMarks(form.getObtainedMarksPicture());
		entity.setObtainedGrade(form.getObtainedGradePicture());
		entity.setNote(form.getNotePicture());
		entity = marksRepository.save(entity);
		
		
		entity = new School1_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("PT");
		entity.setTotal(form.getTotalPT());
		entity.setObtainedMarks(form.getObtainedMarksPT());
		entity.setObtainedGrade(form.getObtainedGradePT());
		entity.setNote(form.getNotePT());
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
		List<School1_MarksEntity> list = marksRepository.findAll();
		
		List<School1_MarksEntity> array = new ArrayList<>();
		List<School1_StudentEntity> array2 = new ArrayList<>();
		for (School1_MarksEntity School1_MarksEntity : list) {
			if (!checkA(array, School1_MarksEntity)) {
				array.add(School1_MarksEntity);
				array2.add(studentRepository.findByRegisterNumber(School1_MarksEntity.getRegisterNumber()));
			}
		}
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Marks");
		response.setBody(array2);
		return response;
	}

	public boolean checkA(List<School1_MarksEntity> array, School1_MarksEntity entity) {
		for (School1_MarksEntity School1_MarksEntity : array) {
			if (School1_MarksEntity.getRegisterNumber().equals(entity.getRegisterNumber())) {
				return true;
			}
		}
		return false;
	}

	public BaseResponse deleteMarks(String registerNumber) {
		BaseResponse response = new BaseResponse();
		List<School1_MarksEntity> list = marksRepository.findByRegisterNumber(registerNumber);
		marksRepository.deleteAll(list);
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Marks Deleted");

		return response;
	}

}
