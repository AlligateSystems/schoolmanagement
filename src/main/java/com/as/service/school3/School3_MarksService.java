package com.as.service.school3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.school3.School3_MarksEntity;
import com.as.form.school3.School3_MarksForm;
import com.as.repository.school3.School3_MarksRepository;

@Service
public class School3_MarksService {

	@Autowired
	School3_MarksRepository marksRepository;

	public BaseResponse addMarksA(School3_MarksForm form) {
		BaseResponse response = new BaseResponse();

		List<School3_MarksEntity> check = marksRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Marks Already");
			return response;
		}
		School3_MarksEntity entity = new School3_MarksEntity();

		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Gujrati");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getGujratiFirst());
		entity.setFirstTest5(form.getGujratiFirst5());
		entity.setSecondTest(form.getGujratiSecond());
		entity.setSecondTest5(form.getGujratiSecond5());
		entity.setNotebook(form.getGujratiNotebook());
		entity.setActivity(form.getGujratiActivity());
		entity.setTotalMarks(form.getGujratiTotalMarks());

		entity = marksRepository.save(entity);

		entity = new School3_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("English");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getEnglishFirst());
		entity.setFirstTest5(form.getEnglishFirst5());
		entity.setSecondTest(form.getEnglishSecond());
		entity.setSecondTest5(form.getEnglishSecond5());
		entity.setNotebook(form.getEnglishNotebook());
		entity.setActivity(form.getEnglishActivity());
		entity.setTotalMarks(form.getEnglishTotalMarks());
		entity = marksRepository.save(entity);

		entity = new School3_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("SocialScience");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getSocialScienceFirst());
		entity.setFirstTest5(form.getSocialScienceFirst5());
		entity.setSecondTest(form.getSocialScienceSecond());
		entity.setSecondTest5(form.getSocialScienceSecond5());
		entity.setNotebook(form.getSocialScienceNotebook());
		entity.setActivity(form.getSocialScienceActivity());
		entity.setTotalMarks(form.getSocialScienceTotalMarks());
		entity = marksRepository.save(entity);

		entity = new School3_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Mathematics");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getMathematicsFirst());
		entity.setFirstTest5(form.getMathematicsFirst5());
		entity.setSecondTest(form.getMathematicsSecond());
		entity.setSecondTest5(form.getMathematicsSecond5());
		entity.setNotebook(form.getMathematicsNotebook());
		entity.setActivity(form.getMathematicsActivity());
		entity.setTotalMarks(form.getMathematicsTotalMarks());
		entity = marksRepository.save(entity);

		entity = new School3_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("ScienceTechnology");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getScienceTechnologyFirst());
		entity.setFirstTest5(form.getScienceTechnologyFirst5());
		entity.setSecondTest(form.getScienceTechnologySecond());
		entity.setSecondTest5(form.getScienceTechnologySecond5());
		entity.setNotebook(form.getScienceTechnologyNotebook());
		entity.setActivity(form.getScienceTechnologyActivity());
		entity.setTotalMarks(form.getScienceTechnologyTotalMarks());
		entity = marksRepository.save(entity);

		entity = new School3_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Computer");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getComputerFirst());
		entity.setFirstTest5(form.getComputerFirst5());
		entity.setSecondTest(form.getComputerSecond());
		entity.setSecondTest5(form.getComputerSecond5());
		entity.setNotebook(form.getComputerNotebook());
		entity.setActivity(form.getComputerActivity());
		entity.setTotalMarks(form.getComputerTotalMarks());
		entity = marksRepository.save(entity);

		entity = new School3_MarksEntity();
		entity.setRollNumber(form.getRollNumber());
		entity.setRegisterNumber(form.getRegisterNumber());
		entity.setSubject("Drawing");
		entity.setTotalTest("80");
		entity.setFirstTest(form.getDrawingFirst());
		entity.setFirstTest5(form.getDrawingFirst5());
		entity.setSecondTest(form.getDrawingSecond());
		entity.setSecondTest5(form.getDrawingSecond5());
		entity.setNotebook(form.getDrawingNotebook());
		entity.setActivity(form.getDrawingActivity());
		entity.setTotalMarks(form.getDrawingTotalMarks());
		entity = marksRepository.save(entity);

		if (entity.getRegisterNumber() != null) {
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Student Marks Added");
			return response;
		}
		return response;
	}

	public BaseResponse addMarksB(School3_MarksForm form) {
		BaseResponse response = new BaseResponse();

		List<School3_MarksEntity> check = marksRepository.findByRegisterNumber(form.getRegisterNumber());
		if (!check.isEmpty()) {
			response.setStatus(200);
			response.setType(ResponseType.WARNING);
			response.setMessage("Student Marks Already");
			return response;
		}
		School3_MarksEntity entity = new School3_MarksEntity();

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

		entity = new School3_MarksEntity();
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

		entity = new School3_MarksEntity();
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

		entity = new School3_MarksEntity();
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

		entity = new School3_MarksEntity();
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

		entity = new School3_MarksEntity();
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

		entity = new School3_MarksEntity();
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
		List<School3_MarksEntity> list = marksRepository.findAll();
		List<School3_MarksEntity> array = new ArrayList<>();
		for (School3_MarksEntity School3_MarksEntity : list) {
			if (!checkA(array, School3_MarksEntity)) {
				array.add(School3_MarksEntity);
			}
		}
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Student Marks");
		response.setBody(array);
		return response;
	}

	public boolean checkA(List<School3_MarksEntity> array, School3_MarksEntity entity) {
		for (School3_MarksEntity School3_MarksEntity : array) {
			if (School3_MarksEntity.getRegisterNumber().equals(entity.getRegisterNumber())) {
				return true;
			}
		}
		return false;
	}

	public BaseResponse deleteMarks(String registerNumber) {
		BaseResponse response = new BaseResponse();
		List<School3_MarksEntity> list = marksRepository.findByRegisterNumber(registerNumber);
		marksRepository.deleteAll(list);
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("Marks Deleted");

		return response;
	}

}
