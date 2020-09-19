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

//	@GetMapping("/school3/result/11_12/{registerNumber}")
//	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
//			HttpServletResponse response, HttpServletRequest request)
//			throws IOException, DocumentException, ParseException {
//
//		School3_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
//		if (student != null) {
////			if (student.getClassIn().equals("11") || student.getClassIn().equals("12")) {
//
//			List<School3_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
//			List<School3_AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);
//
//			try {
//				ClassPathResource cpr = new ClassPathResource(env.getProperty("school3.result.11_12.doc.filePath"));
//				XWPFDocument doc = new XWPFDocument(cpr.getInputStream());
//				/*
//				 * For Table
//				 */
//				for (XWPFTable tbl : doc.getTables()) {
//					for (XWPFTableRow row : tbl.getRows()) {
//						for (XWPFTableCell cell : row.getTableCells()) {
//							for (XWPFParagraph p : cell.getParagraphs()) {
//								for (XWPFRun r : p.getRuns()) {
//									String text = r.getText(0);
//									/*
//									 * For Attendance Table
//									 */
//									for (School3_AttendenceEntity attend : attendaceList) {
//										if (attend.getMonth().equalsIgnoreCase("June")) {
//											if (text != null && text.contains("|t1|")) {
//												text = text.replace("|t1|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h1|")) {
//												text = text.replace("|h1|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("July")) {
//											if (text != null && text.contains("|t2|")) {
//												text = text.replace("|t2|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h2|")) {
//												text = text.replace("|h2|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("August")) {
//											if (text != null && text.contains("|t3|")) {
//												text = text.replace("|t3|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h3|")) {
//												text = text.replace("|h3|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("September")) {
//											if (text != null && text.contains("|t4|")) {
//												text = text.replace("|t4|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h4|")) {
//												text = text.replace("|h4|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("Octomer")) {
//											if (text != null && text.contains("|t5|")) {
//												text = text.replace("|t5|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h5|")) {
//												text = text.replace("|h5|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("November")) {
//											if (text != null && text.contains("|t6|")) {
//												text = text.replace("|t6|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h6|")) {
//												text = text.replace("|h6|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("December")) {
//											if (text != null && text.contains("|t7|")) {
//												text = text.replace("|t7|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h7|")) {
//												text = text.replace("|h7|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("January")) {
//											if (text != null && text.contains("|t8|")) {
//												text = text.replace("|t8|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h8|")) {
//												text = text.replace("|h8|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("February")) {
//											if (text != null && text.contains("|t9|")) {
//												text = text.replace("|t9|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h9|")) {
//												text = text.replace("|h9|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("March")) {
//											if (text != null && text.contains("|t10|")) {
//												text = text.replace("|t10|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h10|")) {
//												text = text.replace("|h10|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("April")) {
//											if (text != null && text.contains("|t11|")) {
//												text = text.replace("|t11|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h11|")) {
//												text = text.replace("|h11|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("May")) {
//											if (text != null && text.contains("|t12|")) {
//												text = text.replace("|t12|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h12|")) {
//												text = text.replace("|h12|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//									}
//
//									/*
//									 * For Marks Table
//									 */
//									for (School3_MarksEntity mark : marksList) {
//										if (mark.getSubject().equalsIgnoreCase("Gujrati")) {
//											if (text != null && text.contains("|a1|")) {
//												text = text.replace("|a1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a2|")) {
//												text = text.replace("|a2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a3|")) {
//												text = text.replace("|a3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a4|")) {
//												text = text.replace("|a4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a5|")) {
//												text = text.replace("|a5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("english")) {
//											if (text != null && text.contains("|b1|")) {
//												text = text.replace("|b1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b2|")) {
//												text = text.replace("|b2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b3|")) {
//												text = text.replace("|b3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b4|")) {
//												text = text.replace("|b4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b5|")) {
//												text = text.replace("|b5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("psychology")) {
//											if (text != null && text.contains("|c1|")) {
//												text = text.replace("|c1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c2|")) {
//												text = text.replace("|c2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c3|")) {
//												text = text.replace("|c3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c4|")) {
//												text = text.replace("|c4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c5|")) {
//												text = text.replace("|c5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("philosophy")) {
//											if (text != null && text.contains("|d1|")) {
//												text = text.replace("|d1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d2|")) {
//												text = text.replace("|d2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d3|")) {
//												text = text.replace("|d3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d4|")) {
//												text = text.replace("|d4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d5|")) {
//												text = text.replace("|d5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("QScripture")) {
//											if (text != null && text.contains("|e1|")) {
//												text = text.replace("|e1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e2|")) {
//												text = text.replace("|e2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e3|")) {
//												text = text.replace("|e3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e4|")) {
//												text = text.replace("|e4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e5|")) {
//												text = text.replace("|e5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("geography")) {
//											if (text != null && text.contains("|f1|")) {
//												text = text.replace("|f1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f2|")) {
//												text = text.replace("|f2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f3|")) {
//												text = text.replace("|f3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f4|")) {
//												text = text.replace("|f4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f5|")) {
//												text = text.replace("|f5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("computer")) {
//											if (text != null && text.contains("|g1|")) {
//												text = text.replace("|g1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g2|")) {
//												text = text.replace("|g2|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g3|")) {
//												text = text.replace("|g3|", mark.getFinalTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g4|")) {
//												text = text.replace("|g4|", mark.getCondonedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g5|")) {
//												text = text.replace("|g5|", mark.getGracePoints());
//												r.setText(text, 0);
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//
//				/*
//				 * For Student Information
//				 */
//				for (XWPFParagraph p : doc.getParagraphs()) {
//					for (XWPFRun r : p.getRuns()) {
//						String text = r.getText(0);
//						if (text != null && text.contains("$name$")) {
//							text = text.replace("$name$", student.getFirstName() + " " + student.getMiddleName() + " "
//									+ student.getLastName());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$standard$")) {
//							text = text.replace("$standard$", student.getClassIn());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$roll$")) {
//							text = text.replace("$roll$", student.getRollNumber());
//							r.setText(text, 0);
//						}
//						r.setText(text, 0);
//					}
//				}
////			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
//				File file = new File("ResultTemp(11-12).docx");
//				doc.write(new FileOutputStream(file));
//				if (file.exists()) {
//					String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//					if (mimeType == null) {
//						mimeType = "application/octet-stream";
//					}
//					response.setContentType(mimeType);
//					response.setContentLength((int) file.length());
//					response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//							"attachment; filename=\"" + registerNumber + "_" + new Date().toString() + ".docx" + "\"");
//					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//					FileCopyUtils.copy(inputStream, response.getOutputStream());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
////			} else {
////				response.sendRedirect("/exception");
////			}
//		} else {
//			response.sendRedirect("/exception");
//		}
//	}

	@GetMapping("/school3/result/11_12/{registerNumber}")
	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {

		School3_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);

		List<School3_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
		List<School3_AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);

		try {
			ClassPathResource cpr = new ClassPathResource(env.getProperty("school3.result.11_12.doc.filePath"));
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
			File file = new File("ResultTempsc31.docx");
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

//	@GetMapping("/school3/result/8_9_10/{registerNumber}")
//	public void getResult8_9_10(@PathVariable(value = "registerNumber") String registerNumber,
//			HttpServletResponse response, HttpServletRequest request)
//			throws IOException, DocumentException, ParseException {
//
//		School3_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
//		if (student != null) {
////			if (student.getClassIn().equals("8") || student.getClassIn().equals("9")
////					|| student.getClassIn().equals("10")) {
//			List<School3_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
//			List<School3_AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);
//
//			try {
//
//				ClassPathResource cpr = new ClassPathResource(env.getProperty("school3.result.8_9_10.doc.filePath"));
//				XWPFDocument doc = new XWPFDocument(cpr.getInputStream());
//				/*
//				 * For Table
//				 */
//				for (XWPFTable tbl : doc.getTables()) {
//					for (XWPFTableRow row : tbl.getRows()) {
//						for (XWPFTableCell cell : row.getTableCells()) {
//							for (XWPFParagraph p : cell.getParagraphs()) {
//								for (XWPFRun r : p.getRuns()) {
//									String text = r.getText(0).trim();
//									/*
//									 * For Attendance Table
//									 */
//									for (School3_AttendenceEntity attend : attendaceList) {
//										if (attend.getMonth().equalsIgnoreCase("June")) {
//											if (text != null && text.contains("|t1|")) {
//												text = text.replace("|t1|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h1|")) {
//												text = text.replace("|h1|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("July")) {
//											if (text != null && text.contains("|t2|")) {
//												text = text.replace("|t2|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h2|")) {
//												text = text.replace("|h2|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("August")) {
//											if (text != null && text.contains("|t3|")) {
//												text = text.replace("|t3|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h3|")) {
//												text = text.replace("|h3|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("September")) {
//											if (text != null && text.contains("|t4|")) {
//												text = text.replace("|t4|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h4|")) {
//												text = text.replace("|h4|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("Octomer")) {
//											if (text != null && text.contains("|t5|")) {
//												text = text.replace("|t5|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h5|")) {
//												text = text.replace("|h5|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("November")) {
//											if (text != null && text.contains("|t6|")) {
//												text = text.replace("|t6|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h6|")) {
//												text = text.replace("|h6|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("December")) {
//											if (text != null && text.contains("|t7|")) {
//												text = text.replace("|t7|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h7|")) {
//												text = text.replace("|h7|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("January")) {
//											if (text != null && text.contains("|t8|")) {
//												text = text.replace("|t8|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h8|")) {
//												text = text.replace("|h8|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("February")) {
//											if (text != null && text.contains("|t9|")) {
//												text = text.replace("|t9|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h9|")) {
//												text = text.replace("|h9|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("March")) {
//											if (text != null && text.contains("|t10|")) {
//												text = text.replace("|t10|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h10|")) {
//												text = text.replace("|h10|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("April")) {
//											if (text != null && text.contains("|t11|")) {
//												text = text.replace("|t11|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h11|")) {
//												text = text.replace("|h11|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//										if (attend.getMonth().equalsIgnoreCase("May")) {
//											if (text != null && text.contains("|t12|")) {
//												text = text.replace("|t12|", attend.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h12|")) {
//												text = text.replace("|h12|", attend.getPresent());
//												r.setText(text, 0);
//											}
//										}
//									}
//
//									/*
//									 * For Marks Table
//									 */
//									for (School3_MarksEntity mark : marksList) {
//										if (mark.getSubject().equalsIgnoreCase("Gujrati")) {
//											if (text != null && text.contains("|a1|")) {
//												text = text.replace("|a1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a2|")) {
//												text = text.replace("|a2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a3|")) {
//												text = text.replace("|a3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a4|")) {
//												text = text.replace("|a4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a5|")) {
//												text = text.replace("|a5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a6|")) {
//												text = text.replace("|a6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a7|")) {
//												text = text.replace("|a7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("english")) {
//											if (text != null && text.contains("|b1|")) {
//												text = text.replace("|b1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b2|")) {
//												text = text.replace("|b2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b3|")) {
//												text = text.replace("|b3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b4|")) {
//												text = text.replace("|b4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b5|")) {
//												text = text.replace("|b5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b6|")) {
//												text = text.replace("|b6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b7|")) {
//												text = text.replace("|b7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("SocialScience")) {
//											if (text != null && text.contains("|c1|")) {
//												text = text.replace("|c1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c2|")) {
//												text = text.replace("|c2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c3|")) {
//												text = text.replace("|c3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c4|")) {
//												text = text.replace("|c4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c5|")) {
//												text = text.replace("|c5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c6|")) {
//												text = text.replace("|c6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c7|")) {
//												text = text.replace("|c7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Mathematics")) {
//											if (text != null && text.contains("|d1|")) {
//												text = text.replace("|d1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d2|")) {
//												text = text.replace("|d2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d3|")) {
//												text = text.replace("|d3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d4|")) {
//												text = text.replace("|d4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d5|")) {
//												text = text.replace("|d5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d6|")) {
//												text = text.replace("|d6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d7|")) {
//												text = text.replace("|d7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("ScienceTechnology")) {
//											if (text != null && text.contains("|e1|")) {
//												text = text.replace("|e1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e2|")) {
//												text = text.replace("|e2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e3|")) {
//												text = text.replace("|e3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e4|")) {
//												text = text.replace("|e4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e5|")) {
//												text = text.replace("|e5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e6|")) {
//												text = text.replace("|e6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e7|")) {
//												text = text.replace("|e7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Computer")) {
//											if (text != null && text.contains("|f1|")) {
//												text = text.replace("|f1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f2|")) {
//												text = text.replace("|f2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f3|")) {
//												text = text.replace("|f3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f4|")) {
//												text = text.replace("|f4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f5|")) {
//												text = text.replace("|f5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f6|")) {
//												text = text.replace("|f6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f7|")) {
//												text = text.replace("|f7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Drawing")) {
//											if (text != null && text.contains("|g1|")) {
//												text = text.replace("|g1|", mark.getFirstTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g2|")) {
//												text = text.replace("|g2|", mark.getFirstTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g3|")) {
//												text = text.replace("|g3|", mark.getSecondTest());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g4|")) {
//												text = text.replace("|g4|", mark.getSecondTest5());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g5|")) {
//												text = text.replace("|g5|", mark.getNotebook());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g6|")) {
//												text = text.replace("|g6|", mark.getActivity());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g7|")) {
//												text = text.replace("|g7|", mark.getTotalMarks());
//												r.setText(text, 0);
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//
//				/*
//				 * For Student Information
//				 */
//				for (XWPFParagraph p : doc.getParagraphs()) {
//					for (XWPFRun r : p.getRuns()) {
//						String text = r.getText(0);
//						if (text != null && text.contains("$name$")) {
//							text = text.replace("$name$", student.getFirstName() + " " + student.getMiddleName() + " "
//									+ student.getLastName());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$standard$")) {
//							text = text.replace("$standard$", student.getClassIn());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$roll$")) {
//							text = text.replace("$roll$", student.getRollNumber());
//							r.setText(text, 0);
//						}
//						r.setText(text, 0);
//					}
//				}
////			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
//				File file = new File("ResultTemp(8-9-10).docx");
//				doc.write(new FileOutputStream(file));
//				if (file.exists()) {
//					String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//					if (mimeType == null) {
//						mimeType = "application/octet-stream";
//					}
//					response.setContentType(mimeType);
//					response.setContentLength((int) file.length());
//					response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//							"attachment; filename=\"" + registerNumber + "_" + new Date().toString() + ".docx" + "\"");
//					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//					FileCopyUtils.copy(inputStream, response.getOutputStream());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
////			} else {
////				response.sendRedirect("/exception");
////			}
//		} else {
//			response.sendRedirect("/exception");
//		}
//	}

	@GetMapping("/school3/result/8_9_10/{registerNumber}")
	public void getResult8_9_10(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {
		School3_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
		List<School3_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
		List<School3_AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);
		try {
			ClassPathResource cpr = new ClassPathResource(env.getProperty("school3.result.8_9_10.doc.filePath"));
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
			File file = new File("ResultTempsc32.docx");
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
