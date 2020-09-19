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

//	@GetMapping("/school2/result/{registerNumber}")
//	public void getResult8_9_10(@PathVariable(value = "registerNumber") String registerNumber,
//			HttpServletResponse response, HttpServletRequest request)
//			throws IOException, DocumentException, ParseException {
//
//		School2_StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
//		if (student != null) {
//			List<School2_MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
//			try {
//				ClassPathResource cpr = new ClassPathResource(env.getProperty("school2.result.doc.filePath"));
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
//									 * For Marks Table
//									 */
//									for (School2_MarksEntity mark : marksList) {
//										if (mark.getSubject().equalsIgnoreCase("qualitySkills")) {
//											if (text != null && text.contains("|a1|")) {
//												text = text.replace("|a1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|a2|")) {
//												text = text.replace("|a2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("generalknowledge")) {
//											if (text != null && text.contains("|b1|")) {
//												text = text.replace("|b1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|b2|")) {
//												text = text.replace("|b2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("language")) {
//											if (text != null && text.contains("|c1|")) {
//												text = text.replace("|c1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|c2|")) {
//												text = text.replace("|c2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("mathematics")) {
//											if (text != null && text.contains("|d1|")) {
//												text = text.replace("|d1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|d2|")) {
//												text = text.replace("|d2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("environmentScience")) {
//											if (text != null && text.contains("|e1|")) {
//												text = text.replace("|e1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|e2|")) {
//												text = text.replace("|e2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("drawing")) {
//											if (text != null && text.contains("|f1|")) {
//												text = text.replace("|f1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|f2|")) {
//												text = text.replace("|f2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//										if (mark.getSubject().equalsIgnoreCase("physicalEducation")) {
//											if (text != null && text.contains("|g1|")) {
//												text = text.replace("|g1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|g2|")) {
//												text = text.replace("|g2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//
//										if (mark.getSubject().equalsIgnoreCase("music")) {
//											if (text != null && text.contains("|h1|")) {
//												text = text.replace("|h1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|h2|")) {
//												text = text.replace("|h2|", mark.getNote());
//												r.setText(text, 0);
//											}
//										}
//
//										if (mark.getSubject().equalsIgnoreCase("total")) {
//											if (text != null && text.contains("|i1|")) {
//												text = text.replace("|i1|", mark.getGrade());
//												r.setText(text, 0);
//											}
//											if (text != null && text.contains("|i2|")) {
//												text = text.replace("|i2|", mark.getNote());
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
//						if (text != null && text.contains("$roll$")) {
//							text = text.replace("$roll$", student.getRegisterNumber());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$std$")) {
//							text = text.replace("$std$", student.getClassIn());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$age$")) {
//							text = text.replace("$age$", student.getAge());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$dob$")) {
//							text = text.replace("$dob$", student.getDateOfBirth());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$id$")) {
//							text = text.replace("$id$", student.getDateInserted());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$ed$")) {
//							text = text.replace("$ed$", student.getEvaluationdate());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$ht$")) {
//							text = text.replace("$ht$", student.getHeight());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("$wt$")) {
//							text = text.replace("$wt$", student.getWeight());
//							r.setText(text, 0);
//						}
//						if (text != null && text.contains("|iq|")) {
//							text = text.replace("|iq|", student.getIQ());
//							r.setText(text, 0);
//						}
//
//						r.setText(text, 0);
//					}
//				}
////			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
//				File file = new File("ResultSc2Temp.docx");
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
//
//		} else {
//			response.sendRedirect("/exception");
//		}
//	}

	@GetMapping("/school2/result/{registerNumber}")
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
				File file = new File("ResultTempsc2.docx");
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
