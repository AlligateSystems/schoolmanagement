package com.as.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.MarksEntity;
import com.as.form.MarksForm;
import com.as.repository.MarksRepository;

@Service
public class MarksService {

	@Autowired
	MarksRepository marksRepository;

	public BaseResponse addMarks(MarksForm form) {
		BaseResponse response = new BaseResponse();

		List<MarksEntity> check = marksRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Marks Already");
			return response;
		}
		MarksEntity entity = new MarksEntity();

		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Gujrati");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getGujratiFirst());
		entity.setSecondTest(form.getGujratiSecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getGujratiFinal());
		entity.setCondonedMarks(form.getGujratiCondoned());
		entity.setGracePoints(form.getGujratiGrace());
		entity = marksRepository.save(entity);

		entity = new MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("English");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getEnglishFirst());
		entity.setSecondTest(form.getEnglishSecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getEnglishFinal());
		entity.setCondonedMarks(form.getEnglishCondoned());
		entity.setGracePoints(form.getEnglishGrace());
		entity = marksRepository.save(entity);

		entity = new MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Psychology");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getPsychologyFirst());
		entity.setSecondTest(form.getPsychologySecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getPsychologyFinal());
		entity.setCondonedMarks(form.getPsychologyCondoned());
		entity.setGracePoints(form.getPsychologyGrace());
		entity = marksRepository.save(entity);

		entity = new MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Philosophy");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getPhilosophyFirst());
		entity.setSecondTest(form.getPhilosophySecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getPhilosophyFinal());
		entity.setCondonedMarks(form.getPhilosophyCondoned());
		entity.setGracePoints(form.getPhilosophyGrace());
		entity = marksRepository.save(entity);

		entity = new MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("QScripture");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getQScriptureFirst());
		entity.setSecondTest(form.getQScriptureSecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getQScriptureFinal());
		entity.setCondonedMarks(form.getQScriptureCondoned());
		entity.setGracePoints(form.getQScriptureGrace());
		entity = marksRepository.save(entity);

		entity = new MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Geography");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getGeographyFirst());
		entity.setSecondTest(form.getGeographySecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getGeographyFinal());
		entity.setCondonedMarks(form.getGeographyCondoned());
		entity.setGracePoints(form.getGeographyGrace());
		entity = marksRepository.save(entity);

		entity = new MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Computer");
		entity.setTotalTest("50");
		entity.setFirstTest(form.getComputerFirst());
		entity.setSecondTest(form.getComputerSecond());
		entity.setFinalTotal("100");
		entity.setFinalTest(form.getComputerFinal());
		entity.setCondonedMarks(form.getComputerCondoned());
		entity.setGracePoints(form.getComputerGrace());
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
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Marks");

		response.setBody(marksRepository.findAll());
		return response;
	}

	public BaseResponse deleteMarks(String registerNumber) {
		BaseResponse response = new BaseResponse();
		List<MarksEntity> list = marksRepository.findByRegisterNumber(registerNumber);
		marksRepository.deleteAll(list);
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Marks Deleted");

		return response;
	}

}
