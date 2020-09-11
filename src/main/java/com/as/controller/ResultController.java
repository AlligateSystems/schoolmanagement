package com.as.controller;

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

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.as.entity.AttendenceEntity;
import com.as.entity.MarksEntity;
import com.as.entity.StudentEntity;
import com.as.repository.AttendanceRepository;
import com.as.repository.MarksRepository;
import com.as.repository.StudentRepository;

@Controller
@RequestMapping("result")
public class ResultController {

	@Autowired
	Environment env;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	MarksRepository marksRepository;

	@Autowired
	AttendanceRepository attendanceRepository;

	@GetMapping("")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("result");
		modelAndView.addObject("pageTitle", "Get Result");
		return modelAndView;
	}

	@GetMapping("/result/{registerNumber}")
	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {

		StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);

		List<MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
		List<AttendenceEntity> attendaceList = attendanceRepository.findByRegisterNumber(registerNumber);

		try {
			XWPFDocument doc = new XWPFDocument(
					OPCPackage.open(ResourceUtils.getFile(env.getProperty("gujrati.result.doc.filePath"))));
			/*
			 * For Table
			 */
			for (XWPFTable tbl : doc.getTables()) {
				for (XWPFTableRow row : tbl.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								/*
								 * For Attendance Table
								 */
								for (AttendenceEntity attend : attendaceList) {
									if (attend.getMonth().equalsIgnoreCase("June")) {
										if (text != null && text.contains("|t1|")) {
											text = text.replace("|t1|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h1|")) {
											text = text.replace("|h1|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("July")) {
										if (text != null && text.contains("|t2|")) {
											text = text.replace("|t2|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h2|")) {
											text = text.replace("|h2|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("August")) {
										if (text != null && text.contains("|t3|")) {
											text = text.replace("|t3|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h3|")) {
											text = text.replace("|h3|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("September")) {
										if (text != null && text.contains("|t4|")) {
											text = text.replace("|t4|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h4|")) {
											text = text.replace("|h4|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("Octomer")) {
										if (text != null && text.contains("|t5|")) {
											text = text.replace("|t5|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h5|")) {
											text = text.replace("|h5|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("November")) {
										if (text != null && text.contains("|t6|")) {
											text = text.replace("|t6|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h6|")) {
											text = text.replace("|h6|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("December")) {
										if (text != null && text.contains("|t7|")) {
											text = text.replace("|t7|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h7|")) {
											text = text.replace("|h7|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("January")) {
										if (text != null && text.contains("|t8|")) {
											text = text.replace("|t8|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h8|")) {
											text = text.replace("|h8|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("February")) {
										if (text != null && text.contains("|t9|")) {
											text = text.replace("|t9|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h9|")) {
											text = text.replace("|h9|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("March")) {
										if (text != null && text.contains("|t10|")) {
											text = text.replace("|t10|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h10|")) {
											text = text.replace("|h10|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("April")) {
										if (text != null && text.contains("|t11|")) {
											text = text.replace("|t11|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h11|")) {
											text = text.replace("|h11|", attend.getPresent());
											r.setText(text, 0);
										}
									}
									if (attend.getMonth().equalsIgnoreCase("May")) {
										if (text != null && text.contains("|t12|")) {
											text = text.replace("|t12|", attend.getTotal());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|h12|")) {
											text = text.replace("|h12|", attend.getPresent());
											r.setText(text, 0);
										}
									}
								}

								/*
								 * For Marks Table
								 */
								for (MarksEntity mark : marksList) {
									if (mark.getSubject().equalsIgnoreCase("Gujrati")) {
										if (text != null && text.contains("|a1|")) {
											text = text.replace("|a1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|a2|")) {
											text = text.replace("|a2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|a3|")) {
											text = text.replace("|a3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|a4|")) {
											text = text.replace("|a4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|a5|")) {
											text = text.replace("|a5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
									if (mark.getSubject().equalsIgnoreCase("english")) {
										if (text != null && text.contains("|b1|")) {
											text = text.replace("|b1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|b2|")) {
											text = text.replace("|b2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|b3|")) {
											text = text.replace("|b3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|b4|")) {
											text = text.replace("|b4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|b5|")) {
											text = text.replace("|b5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
									if (mark.getSubject().equalsIgnoreCase("psychology")) {
										if (text != null && text.contains("|c1|")) {
											text = text.replace("|c1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|c2|")) {
											text = text.replace("|c2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|c3|")) {
											text = text.replace("|c3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|c4|")) {
											text = text.replace("|c4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|c5|")) {
											text = text.replace("|c5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
									if (mark.getSubject().equalsIgnoreCase("philosophy")) {
										if (text != null && text.contains("|d1|")) {
											text = text.replace("|d1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|d2|")) {
											text = text.replace("|d2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|d3|")) {
											text = text.replace("|d3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|d4|")) {
											text = text.replace("|d4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|d5|")) {
											text = text.replace("|d5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
									if (mark.getSubject().equalsIgnoreCase("QScripture")) {
										if (text != null && text.contains("|e1|")) {
											text = text.replace("|e1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|e2|")) {
											text = text.replace("|e2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|e3|")) {
											text = text.replace("|e3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|e4|")) {
											text = text.replace("|e4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|e5|")) {
											text = text.replace("|e5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
									if (mark.getSubject().equalsIgnoreCase("geography")) {
										if (text != null && text.contains("|f1|")) {
											text = text.replace("|f1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|f2|")) {
											text = text.replace("|f2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|f3|")) {
											text = text.replace("|f3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|f4|")) {
											text = text.replace("|f4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|f5|")) {
											text = text.replace("|f5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
									if (mark.getSubject().equalsIgnoreCase("computer")) {
										if (text != null && text.contains("|g1|")) {
											text = text.replace("|g1|", mark.getFirstTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|g2|")) {
											text = text.replace("|g2|", mark.getSecondTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|g3|")) {
											text = text.replace("|g3|", mark.getFinalTest());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|g4|")) {
											text = text.replace("|g4|", mark.getCondonedMarks());
											r.setText(text, 0);
										}
										if (text != null && text.contains("|g5|")) {
											text = text.replace("|g5|", mark.getGracePoints());
											r.setText(text, 0);
										}
									}
								}
							}
						}
					}
				}
			}

			/*
			 * For Student Information
			 */
			for (XWPFParagraph p : doc.getParagraphs()) {
				for (XWPFRun r : p.getRuns()) {
					String text = r.getText(0);
					if (text != null && text.contains("$name$")) {
						text = text.replace("$name$",
								student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
						r.setText(text, 0);
					}
					if (text != null && text.contains("$standard$")) {
						text = text.replace("$standard$", "12 HSC");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$roll$")) {
						text = text.replace("$roll$", student.getRollNumber());
						r.setText(text, 0);
					}
					r.setText(text, 0);
				}
			}
//			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
			File file = new File("ResultTemp.docx");
			doc.write(new FileOutputStream(file));
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

//	@GetMapping("/result/{registerNumber}")
//	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
//			HttpServletResponse response, HttpServletRequest request)
//			throws IOException, DocumentException, ParseException {
//
//		StudentEntity student = studentRepository.findByRegisterNumber(registerNumber);
//
//		List<MarksEntity> marksList = marksRepository.findByRegisterNumber(registerNumber);
//
//		response.setContentType("application/pdf;charset=UTF-8");
//		response.addHeader("Content-Disposition", "inline; filename=" + "print1.pdf");
////		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "print1.pdf" + "\"");
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		String FONT = env.getProperty("gujrati.font.filePath");
//		PdfDocument pdfDoc = new PdfDocument(new PdfReader(env.getProperty("gujrati.bonafide_certificate.pdf.filePath")),
//				new PdfWriter(baos));
//		PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
//		form.setGenerateAppearance(true);
//		PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
//
//		form.getField("name").setValue(
//				student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName(), font, 12f);
//		form.getField("standard").setValue(student.getClassIn(), font, 12f);
//		form.getField("rollNumber").setValue(student.getRollNumber(), font, 12f);
//
//		for (MarksEntity mark : marksList) {
//			if (mark.getSubject().equalsIgnoreCase("Gujrati")) {
//				form.getField("gujratiFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("gujratiSecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("gujratiFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("gujratiCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("gujratiGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//			if (mark.getSubject().equalsIgnoreCase("english")) {
//				form.getField("englishFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("englishSecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("englishFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("englishCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("englishGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//			if (mark.getSubject().equalsIgnoreCase("psychology")) {
//				form.getField("psychologyFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("psychologySecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("psychologyFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("psychologyCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("psychologyGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//			if (mark.getSubject().equalsIgnoreCase("philosophy")) {
//				form.getField("philosophyFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("philosophySecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("philosophyFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("philosophyCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("philosophyGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//			if (mark.getSubject().equalsIgnoreCase("QScripture")) {
//				form.getField("qScriptureFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("qScriptureSecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("qScriptureFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("qScriptureCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("qScriptureGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//			if (mark.getSubject().equalsIgnoreCase("geography")) {
//				form.getField("geographyFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("geographySecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("geographyFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("geographyCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("geographyGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//			if (mark.getSubject().equalsIgnoreCase("computer")) {
//				form.getField("computerFirst").setValue(mark.getFirstTest(), font, 12f);
//				form.getField("computerSecond").setValue(mark.getSecondTest(), font, 12f);
//				form.getField("computerFinal").setValue(mark.getFinalTest(), font, 12f);
//				form.getField("computerCondoned").setValue(mark.getCondonedMarks(), font, 12f);
//				form.getField("computerGrace").setValue(mark.getGracePoints(), font, 12f);
//			}
//
//		}
//		;
//
//		pdfDoc.close();
//		ServletOutputStream out = response.getOutputStream();
//		baos.writeTo(out);
//		out.flush();
//	}
}
