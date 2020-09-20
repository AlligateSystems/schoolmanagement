package com.as.controller.school2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.as.entity.school2.School2_MarksEntity;
import com.as.entity.school2.School2_StudentEntity;
import com.as.repository.school2.School2_MarksRepository;
import com.as.repository.school2.School2_StudentRepository;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

@Controller
public class School2_ResultController {

	@Autowired
	Environment env;

	@Autowired
	School2_StudentRepository studentRepository;

	@Autowired
	School2_MarksRepository marksRepository;

	 @PostMapping("/school2/result/check/{registerNumber}")
		public @ResponseBody BaseResponse check(@PathVariable(value = "registerNumber") String registerNumber) {
			BaseResponse baseResponse=new BaseResponse();
			School2_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
			List<School2_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
			if (student == null) {
				baseResponse.setStatus(300);
				baseResponse.setType(ResponseType.RESPONSE_ERROR);
				baseResponse.setMessage("Student Not Exits");
				return baseResponse;
			}
			if(marksList.isEmpty()) {
				baseResponse.setStatus(300);
				baseResponse.setType(ResponseType.RESPONSE_ERROR);
				baseResponse.setMessage("Marks Not Added");
				return baseResponse;
			}
			
			baseResponse.setStatus(200);
			return baseResponse;
		}

	@GetMapping("/school2/result/get/{registerNumber}")
	public void getResult8_9_10(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {

		School2_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
		if (student != null) {
			List<School2_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
			try {
				ClassPathResource cpr = new ClassPathResource(env.getProperty("school2.result.doc.filePath"));
				Docx docx = new Docx(cpr.getInputStream());
				docx.setVariablePattern(new VariablePattern("#{", "}"));
				List<String> findVariables = docx.findVariables();
				for (String var : findVariables) {
					System.out.println("VARIABLE => " + var);
				}
				Variables var = new Variables();

				var.addTextVariable(new TextVariable("#{name}",
						student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName()));
				var.addTextVariable(new TextVariable("#{std}", student.getClassIn()));
				var.addTextVariable(new TextVariable("#{roll}", student.getRegisterNumber()));
				var.addTextVariable(new TextVariable("#{age}", student.getAge()));
				SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
				String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
				var.addTextVariable(new TextVariable("#{dob}", reformattedStr));
				var.addTextVariable(new TextVariable("#{id}", student.getDateInserted()));
				var.addTextVariable(new TextVariable("#{ed}", student.getEvaluationdate()));
				var.addTextVariable(new TextVariable("#{ht}", student.getHeight()));
				var.addTextVariable(new TextVariable("#{wt}", student.getWeight()));
				var.addTextVariable(new TextVariable("#{iq}", student.getIQ()));

				for (School2_MarksEntity mark : marksList) {
					if (mark.getSubject().equalsIgnoreCase("qualitySkills")) {
						var.addTextVariable(new TextVariable("#{a1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{a2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("generalknowledge")) {
						var.addTextVariable(new TextVariable("#{b1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{b2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("language")) {
						var.addTextVariable(new TextVariable("#{c1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{c2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("mathematics")) {
						var.addTextVariable(new TextVariable("#{d1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{d2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("environmentScience")) {
						var.addTextVariable(new TextVariable("#{e1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{e2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("drawing")) {
						var.addTextVariable(new TextVariable("#{f1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{f2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("physicalEducation")) {
						var.addTextVariable(new TextVariable("#{g1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{g2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("music")) {
						var.addTextVariable(new TextVariable("#{h1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{h2}", mark.getNote()));
					}
					if (mark.getSubject().equalsIgnoreCase("total")) {
						var.addTextVariable(new TextVariable("#{i1}", mark.getGrade()));
						var.addTextVariable(new TextVariable("#{i2}", mark.getNote()));
					}
				}

				docx.fillTemplate(var);
				File file = new File("Temp_SC2_Result.docx");
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

		} else {
			response.sendRedirect("/exception");
		}
	}

}
