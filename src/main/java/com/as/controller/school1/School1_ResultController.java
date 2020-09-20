package com.as.controller.school1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.as.config.BaseResponse;
import com.as.config.ResponseType;
import com.as.entity.school1.School1_MarksEntity;
import com.as.entity.school1.School1_StudentEntity;
import com.as.repository.school1.School1_MarksRepository;
import com.as.repository.school1.School1_StudentRepository;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

@Controller
public class School1_ResultController {

	@Autowired
	Environment env;

	@Autowired
	School1_StudentRepository studentRepository;

	@Autowired
	School1_MarksRepository marksRepository;

	@PostMapping("/school1/result/check/{registerNumber}")
	public @ResponseBody BaseResponse check(@PathVariable(value = "registerNumber") String registerNumber) {
		BaseResponse baseResponse = new BaseResponse();
		School1_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
		List<School1_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
		if (student == null) {
			baseResponse.setStatus(300);
			baseResponse.setType(ResponseType.RESPONSE_ERROR);
			baseResponse.setMessage("Student Not Exist");
			return baseResponse;
		}
		if (marksList.isEmpty()) {
			baseResponse.setStatus(300);
			baseResponse.setType(ResponseType.RESPONSE_ERROR);
			baseResponse.setMessage("Marks Not Added");
			return baseResponse;
		}

		baseResponse.setStatus(200);
		return baseResponse;
	}

	@GetMapping("/school1/result/get/{registerNumber}")
	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {

		School1_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber.trim());
		List<School1_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber.trim());

		try {
			ClassPathResource cpr = new ClassPathResource(env.getProperty("school1.result.doc.filePath"));
			Docx docx = new Docx(cpr.getInputStream());
			docx.setVariablePattern(new VariablePattern("#{", "}"));
			Variables var = new Variables();

			var.addTextVariable(new TextVariable("#{name}",
					student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName()));
			var.addTextVariable(new TextVariable("#{std}", student.getClassIn()));
			var.addTextVariable(new TextVariable("#{reg}", student.getRegisterNumber()));

			for (School1_MarksEntity mark : marksList) {
				if (mark.getSubject().equalsIgnoreCase("Gujarati")) {
					var.addTextVariable(new TextVariable("#{a1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{a2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{a3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{a4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("Mathematics")) {
					var.addTextVariable(new TextVariable("#{b1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{b2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{b3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{b4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("ScienceTechnology")) {
					var.addTextVariable(new TextVariable("#{c1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{c2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{c3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{c4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("SocialScience")) {
					var.addTextVariable(new TextVariable("#{d1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{d2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{d3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{d4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("Hindi")) {
					var.addTextVariable(new TextVariable("#{e1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{e2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{e3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{e4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("English")) {
					var.addTextVariable(new TextVariable("#{f1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{f2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{f3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{f4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("Sanskrit")) {
					var.addTextVariable(new TextVariable("#{g1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{g2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{g3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{g4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("Industry")) {
					var.addTextVariable(new TextVariable("#{h1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{h2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{h3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{h4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("Computer")) {
					var.addTextVariable(new TextVariable("#{i1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{i2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{i3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{i4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("Picture")) {
					var.addTextVariable(new TextVariable("#{j1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{j2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{j3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{j4}", mark.getNote()));
				}

				if (mark.getSubject().equalsIgnoreCase("PT")) {
					var.addTextVariable(new TextVariable("#{k1}", mark.getTotal()));
					var.addTextVariable(new TextVariable("#{k2}", mark.getObtainedMarks()));
					var.addTextVariable(new TextVariable("#{k3}", mark.getObtainedGrade()));
					var.addTextVariable(new TextVariable("#{k4}", mark.getNote()));
				}
			}

			docx.fillTemplate(var);
			File file = new File("Temp_SC1_Result.docx");
			docx.save(new FileOutputStream(file));
			if (file.exists()) {
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) file.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + registerNumber + "_" + new Date().toString() + ".docx" + "\"");
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
