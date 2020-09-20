package com.as.controller.school3;

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
import com.as.entity.school3.School3_AttendenceEntity;
import com.as.entity.school3.School3_MarksEntity;
import com.as.entity.school3.School3_StudentEntity;
import com.as.repository.school3.School3_AttendanceRepository;
import com.as.repository.school3.School3_MarksRepository;
import com.as.repository.school3.School3_StudentRepository;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

@Controller
public class School3_ResultController {

	@Autowired
	Environment env;

	@Autowired
	School3_StudentRepository studentRepository;

	@Autowired
	School3_MarksRepository marksRepository;

	@Autowired
	School3_AttendanceRepository attendanceRepository;

	@PostMapping("/school3/result/check/{registerNumber}")
	public @ResponseBody BaseResponse check(@PathVariable(value = "registerNumber") String registerNumber) {
		BaseResponse baseResponse=new BaseResponse();
		School3_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
		List<School3_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
		List<School3_AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);
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
		if(attendaceList.isEmpty()) {
			baseResponse.setStatus(300);
			baseResponse.setType(ResponseType.RESPONSE_ERROR);
			baseResponse.setMessage("Attendace Not Added");
			return baseResponse;
		}
		
		baseResponse.setStatus(200);
		return baseResponse;
	}
	
	@GetMapping("/school3/result/get/{registerNumber}")
	public void getResult8_9_10(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {
		School3_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
		List<School3_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
		List<School3_AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);

		if (student.getClassIn().equals("૧૧") || student.getClassIn().equals("૧૨")) {

			try {
				ClassPathResource cpr = new ClassPathResource(env.getProperty("school3.result.11_12.doc.filePath"));
				Docx docx = new Docx(cpr.getInputStream());
				docx.setVariablePattern(new VariablePattern("#{", "}"));
				Variables var = new Variables();
				var.addTextVariable(new TextVariable("#{name}",
						student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName()));
				var.addTextVariable(new TextVariable("#{std}", student.getClassIn()));
				var.addTextVariable(new TextVariable("#{roll}", student.getRollNumber()));
				for (School3_AttendenceEntity attend : attendaceList) {
					if (attend.getMonth().equalsIgnoreCase("June")) {
						var.addTextVariable(new TextVariable("#{t1}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h1}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("July")) {
						var.addTextVariable(new TextVariable("#{t2}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h2}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("August")) {
						var.addTextVariable(new TextVariable("#{t3}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h3}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("September")) {
						var.addTextVariable(new TextVariable("#{t4}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h4}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("Octomer")) {
						var.addTextVariable(new TextVariable("#{t5}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h5}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("November")) {
						var.addTextVariable(new TextVariable("#{t6}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h6}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("December")) {
						var.addTextVariable(new TextVariable("#{t7}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h7}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("January")) {
						var.addTextVariable(new TextVariable("#{t8}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h8}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("February")) {
						var.addTextVariable(new TextVariable("#{t9}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h9}", attend.getPresent()));
					}

					if (attend.getMonth().equalsIgnoreCase("March")) {
						var.addTextVariable(new TextVariable("#{t10}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h10}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("April")) {
						var.addTextVariable(new TextVariable("#{t11}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h11}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("May")) {
						var.addTextVariable(new TextVariable("#{t12}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h12}", attend.getPresent()));
					}
				}

				for (School3_MarksEntity mark : marksList) {
					if (mark.getSubject().equalsIgnoreCase("Gujrati")) {
						var.addTextVariable(new TextVariable("#{a1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{a2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{a3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{a4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{a5}", mark.getGracePoints()));
					}
					if (mark.getSubject().equalsIgnoreCase("english")) {
						var.addTextVariable(new TextVariable("#{b1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{b2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{b3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{b4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{b5}", mark.getGracePoints()));
					}
					if (mark.getSubject().equalsIgnoreCase("psychology")) {
						var.addTextVariable(new TextVariable("#{c1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{c2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{c3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{c4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{c5}", mark.getGracePoints()));
					}
					if (mark.getSubject().equalsIgnoreCase("philosophy")) {
						var.addTextVariable(new TextVariable("#{d1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{d2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{d3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{d4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{d5}", mark.getGracePoints()));
					}
					if (mark.getSubject().equalsIgnoreCase("QScripture")) {
						var.addTextVariable(new TextVariable("#{e1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{e2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{e3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{e4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{e5}", mark.getGracePoints()));
					}
					if (mark.getSubject().equalsIgnoreCase("geography")) {
						var.addTextVariable(new TextVariable("#{f1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{f2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{f3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{f4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{f5}", mark.getGracePoints()));
					}
					if (mark.getSubject().equalsIgnoreCase("computer")) {
						var.addTextVariable(new TextVariable("#{g1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{g2}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{g3}", mark.getFinalTest()));
						var.addTextVariable(new TextVariable("#{g4}", mark.getCondonedMarks()));
						var.addTextVariable(new TextVariable("#{g5}", mark.getGracePoints()));
					}

				}

				docx.fillTemplate(var);
				File file = new File("Temp_SC3_Result_1.docx");
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

			try {
				ClassPathResource cpr = new ClassPathResource(env.getProperty("school3.result.8_9_10.doc.filePath"));
				Docx docx = new Docx(cpr.getInputStream());
				docx.setVariablePattern(new VariablePattern("#{", "}"));
				Variables var = new Variables();
				var.addTextVariable(new TextVariable("#{name}",
						student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName()));
				var.addTextVariable(new TextVariable("#{std}", student.getClassIn()));
				var.addTextVariable(new TextVariable("#{roll}", student.getRegisterNumber()));

				for (School3_AttendenceEntity attend : attendaceList) {
					if (attend.getMonth().equalsIgnoreCase("June")) {
						var.addTextVariable(new TextVariable("#{t1}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h1}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("July")) {
						var.addTextVariable(new TextVariable("#{t2}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h2}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("August")) {
						var.addTextVariable(new TextVariable("#{t3}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h3}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("September")) {
						var.addTextVariable(new TextVariable("#{t4}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h4}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("Octomer")) {
						var.addTextVariable(new TextVariable("#{t5}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h5}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("November")) {
						var.addTextVariable(new TextVariable("#{t6}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h6}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("December")) {
						var.addTextVariable(new TextVariable("#{t7}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h7}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("January")) {
						var.addTextVariable(new TextVariable("#{t8}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h8}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("February")) {
						var.addTextVariable(new TextVariable("#{t9}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h9}", attend.getPresent()));
					}

					if (attend.getMonth().equalsIgnoreCase("March")) {
						var.addTextVariable(new TextVariable("#{t10}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h10}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("April")) {
						var.addTextVariable(new TextVariable("#{t11}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h11}", attend.getPresent()));
					}
					if (attend.getMonth().equalsIgnoreCase("May")) {
						var.addTextVariable(new TextVariable("#{t12}", attend.getTotal()));
						var.addTextVariable(new TextVariable("#{h12}", attend.getPresent()));
					}
				}

				for (School3_MarksEntity mark : marksList) {
					if (mark.getSubject().equalsIgnoreCase("Gujrati")) {
						var.addTextVariable(new TextVariable("#{a1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{a2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{a3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{a4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{a5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{a6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{a7}", mark.getTotalMarks()));
					}
					if (mark.getSubject().equalsIgnoreCase("english")) {
						var.addTextVariable(new TextVariable("#{b1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{b2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{b3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{b4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{b5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{b6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{b7}", mark.getTotalMarks()));
					}
					if (mark.getSubject().equalsIgnoreCase("SocialScience")) {
						var.addTextVariable(new TextVariable("#{c1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{c2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{c3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{c4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{c5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{c6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{c7}", mark.getTotalMarks()));
					}
					if (mark.getSubject().equalsIgnoreCase("Mathematics")) {
						var.addTextVariable(new TextVariable("#{d1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{d2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{d3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{d4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{d5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{d6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{d7}", mark.getTotalMarks()));
					}
					if (mark.getSubject().equalsIgnoreCase("ScienceTechnology")) {
						var.addTextVariable(new TextVariable("#{e1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{e2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{e3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{e4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{e5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{e6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{e7}", mark.getTotalMarks()));
					}
					if (mark.getSubject().equalsIgnoreCase("Computer")) {
						var.addTextVariable(new TextVariable("#{f1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{f2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{f3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{f4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{f5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{f6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{f7}", mark.getTotalMarks()));
					}
					if (mark.getSubject().equalsIgnoreCase("Drawing")) {
						var.addTextVariable(new TextVariable("#{g1}", mark.getFirstTest()));
						var.addTextVariable(new TextVariable("#{g2}", mark.getFirstTest5()));
						var.addTextVariable(new TextVariable("#{g3}", mark.getSecondTest()));
						var.addTextVariable(new TextVariable("#{g4}", mark.getSecondTest5()));
						var.addTextVariable(new TextVariable("#{g5}", mark.getNotebook()));
						var.addTextVariable(new TextVariable("#{g6}", mark.getActivity()));
						var.addTextVariable(new TextVariable("#{g7}", mark.getTotalMarks()));
					}
				}
				docx.fillTemplate(var);
				File file = new File("Temp_SC3_Result_2.docx");
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
}
