//package com.as.controller;
//
//import java.io.BufferedInputStream;
////import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
////import java.io.FileOutputStream;
////import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLConnection;
////import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
////import javax.servlet.ServletOutputStream;
////import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
////import org.dom4j.DocumentException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.as.entity.StudentEntity;
//import com.as.repository.StudentRepository;
////import com.itextpdf.forms.PdfAcroForm;
////import com.itextpdf.io.font.PdfEncodings;
////import com.itextpdf.kernel.font.PdfFont;
////import com.itextpdf.kernel.font.PdfFontFactory;
////import com.itextpdf.kernel.pdf.PdfDocument;
////import com.itextpdf.kernel.pdf.PdfReader;
////import com.itextpdf.kernel.pdf.PdfWriter;
//
//@Controller
//@RequestMapping("bonafide")
//public class BonafideController {
//
//	@Autowired
//	Environment env;
//
//	@Autowired
//	School3_StudentRepository repository;
//
//	@GetMapping("")
//	public ModelAndView attendance() {
//		ModelAndView modelAndView = new ModelAndView("bonafide");
//		modelAndView.addObject("pageTitle", "Get Bonafide");
//		return modelAndView;
//	}
//
////	@GetMapping("/bonafide/{registerNumber}")
////	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
////			HttpServletResponse response, HttpServletRequest request)
////			throws IOException, DocumentException, ParseException {
////		System.out.println(registerNumber);
////		StudentEntity student = repository.findByRegisterNumber(registerNumber);
////
////		response.setContentType("application/pdf;charset=UTF-8");
////		response.addHeader("Content-Disposition", "inline; filename=" + "print1.pdf");
//////		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "print1.pdf" + "\"");
////
////		ByteArrayOutputStream baos = new ByteArrayOutputStream();
////		String FONT = env.getProperty("gujrati.font.filePath");
////		PdfDocument pdfDoc = new PdfDocument(
////				new PdfReader(env.getProperty("gujrati.bonafide_certificate.pdf.filePath")), new PdfWriter(baos));
////		PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
////		form.setGenerateAppearance(true);
////		PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
////		form.getField("srno").setValue("1234", font, 12f);
////		form.getField("date").setValue(new Date().toString(), font, 12f);
////		form.getField("name").setValue(
////				student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName(), font, 12f);
////		form.getField("year").setValue("2020", font, 12f);
////		form.getField("cast").setValue(student.getCaste(), font, 12f);
////		form.getField("standard").setValue("12 HSC", font, 12f);
////
////		SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
////		SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
////		String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
////		form.getField("dob").setValue(reformattedStr, font, 12f);
////
////		form.getField("registerNumber").setValue(student.getRegisterNumber(), font, 12f);
////		pdfDoc.close();
////		ServletOutputStream out = response.getOutputStream();
////		baos.writeTo(out);
////		out.flush();
////	}
//	
//	
//	@GetMapping("/bonafide/{registerNumber}")
//	public void dashboard(@PathVariable(value = "registerNumber") String registerNumber, HttpServletResponse response) {
//		StudentEntity student = repository.findByRegisterNumber(registerNumber);
//		try {
//			XWPFDocument doc = new XWPFDocument(
//					OPCPackage.open(ResourceUtils.getFile(env.getProperty("gujrati.bonafide_certificate.doc.filePath"))));
//			LocalDate dt = LocalDate.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yy");
//			for (XWPFParagraph p : doc.getParagraphs()) {
//				for (XWPFRun r : p.getRuns()) {
//					String text = r.getText(0);
//					if (text != null && text.contains("$date$")) {
//						text = text.replace("$date$", formatter.format(dt));
//						r.setText(text, 0);
//					}
//					if (text != null && text.contains("$name$")) {
//						text = text.replace("$name$", student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
//						r.setText(text, 0);
//					}
//					if (text != null && text.contains("$standard$")) {
//						text = text.replace("$standard$", student.getClassIn());
//						r.setText(text, 0);
//					}
//					if (text != null && text.contains("$year$")) {
//						text = text.replace("$year$", "2020");
//						r.setText(text, 0);
//					}
//					if (text != null && text.contains("$dob$")) {
//						SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
//						SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
//						String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
//						text = text.replace("$dob$", reformattedStr);
//						r.setText(text, 0);
//					}
//					if (text != null && text.contains("$cast$")) {
//						text = text.replace("$cast$", student.getCaste());
//						r.setText(text, 0);
//					}
//					if (text != null && text.contains("$registerNumber$")) {
//						text = text.replace("$registerNumber$", student.getRegisterNumber());
//						r.setText(text, 0);
//					}
//					r.setText(text, 0);
//				}
//			}
////			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
//			File file = new File("BonafideTemp.docx");
//			doc.write(new FileOutputStream(file));
//			if (file.exists()) {
//				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//				if (mimeType == null) {
//					mimeType = "application/octet-stream";
//				}
//				response.setContentType(mimeType);
//				response.setContentLength((int) file.length());
//				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + registerNumber+"_"+new Date().toString()+".docx" + "\"");
//				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//				FileCopyUtils.copy(inputStream, response.getOutputStream());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
