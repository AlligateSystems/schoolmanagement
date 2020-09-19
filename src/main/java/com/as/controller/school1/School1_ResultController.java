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

//	@GetMapping("/school1/result/{registerNumber}")
//	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
//			HttpServletResponse response, HttpServletRequest request)
//			throws IOException, DocumentException, ParseException {
//
//		School1_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
//		if (student != null) {
//
//			List<School1_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
//
//			try {
//				ClassPathResource cpr = new ClassPathResource(env.getProperty("school1.result.doc.filePath"));
//				
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
//									 * For Marks Table
//									 */
//									for (School1_MarksEntity mark : marksList) {
//										if (mark.getSubject().equalsIgnoreCase("Gujarati")) {
//											if (text != null && text.contains("|a1|")) {
//												text = text.replace("|a1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a2|")) {
//												text = text.replace("|a2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a3|")) {
//												text = text.replace("|a3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a4|")) {
//												text = text.replace("|a4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Mathematics")) {
//											if (text != null && text.contains("|b1|")) {
//												text = text.replace("|b1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b2|")) {
//												text = text.replace("|b2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b3|")) {
//												text = text.replace("|b3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b4|")) {
//												text = text.replace("|b4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("ScienceTechnology")) {
//											if (text != null && text.contains("|c1|")) {
//												text = text.replace("|c1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c2|")) {
//												text = text.replace("|c2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c3|")) {
//												text = text.replace("|c3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c4|")) {
//												text = text.replace("|c4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("SocialScience")) {
//											if (text != null && text.contains("|d1|")) {
//												text = text.replace("|d1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d2|")) {
//												text = text.replace("|d2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d3|")) {
//												text = text.replace("|d3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d4|")) {
//												text = text.replace("|d4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Hindi")) {
//											if (text != null && text.contains("|e1|")) {
//												text = text.replace("|e1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e2|")) {
//												text = text.replace("|e2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e3|")) {
//												text = text.replace("|e3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e4|")) {
//												text = text.replace("|e4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("English")) {
//											if (text != null && text.contains("|f1|")) {
//												text = text.replace("|f1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f2|")) {
//												text = text.replace("|f2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f3|")) {
//												text = text.replace("|f3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f4|")) {
//												text = text.replace("|f4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Sanskrit")) {
//											if (text != null && text.contains("|g1|")) {
//												text = text.replace("|g1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g2|")) {
//												text = text.replace("|g2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g3|")) {
//												text = text.replace("|g3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g4|")) {
//												text = text.replace("|g4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//
//										if (mark.getSubject().equalsIgnoreCase("Industry")) {
//											if (text != null && text.contains("|h1|")) {
//												text = text.replace("|h1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h2|")) {
//												text = text.replace("|h2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h3|")) {
//												text = text.replace("|h3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h4|")) {
//												text = text.replace("|h4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Computer")) {
//											if (text != null && text.contains("|i1|")) {
//												text = text.replace("|i1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|i2|")) {
//												text = text.replace("|i2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|i3|")) {
//												text = text.replace("|i3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|i4|")) {
//												text = text.replace("|i4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("Picture")) {
//											if (text != null && text.contains("|j1|")) {
//												text = text.replace("|j1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|j2|")) {
//												text = text.replace("|j2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|j3|")) {
//												text = text.replace("|j3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|j4|")) {
//												text = text.replace("|j4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("PT")) {
//											if (text != null && text.contains("|k1|")) {
//												text = text.replace("|k1|", mark.getTotal());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|k2|")) {
//												text = text.replace("|k2|", mark.getObtainedMarks());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|k3|")) {
//												text = text.replace("|k3|", mark.getObtainedGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|k4|")) {
//												text = text.replace("|k4|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//
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
//						if (text != null && text.contains("$std$")) {
//							text = text.replace("$std$", student.getClassIn());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$reg$")) {
//							text = text.replace("$reg$", student.getRegisterNumber());
//							r.setText(text, 0);
//						}
//						r.setText(text, 0);
//					}
//				}
////			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
//				File file = new File("ResultTempsc1.docx");
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
//		} else {
//			response.sendRedirect("/exception");
//		}
//	}

	@GetMapping("/school1/result/{registerNumber}")
	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {

		School1_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
		List<School1_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);

		try {
			ClassPathResource cpr = new ClassPathResource(env.getProperty("school1.result.doc.filePath"));
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
			File file = new File("ResultTempsc1.docx");
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
