package com.as.controller.school1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.as.entity.school1.School1_StudentEntity;
import com.as.repository.school1.School1_StudentRepository;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

@Controller
public class School1_BonafideController {

	@Autowired
	Environment env;

	@Autowired
	School1_StudentRepository repository;

	@GetMapping("/school1/bonafide")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("school1/bonafide");
		modelAndView.addObject("pageTitle", "Get Bonafide");
		return modelAndView;
	}

//	@GetMapping("/school1/bonafide/{registerNumber}")
//	public void dashboard(@PathVariable(value = "registerNumber") String registerNumber, HttpServletResponse response) {
//		School1_StudentEntity student = repository.findByRegisterNumber(registerNumber);
//		try {
//
//			ClassPathResource cpr = new ClassPathResource(env.getProperty("school1.bonafide_certificate.doc.filePath"));
//			XWPFDocument doc = new XWPFDocument(cpr.getInputStream());
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
//						text = text.replace("$name$",
//								student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
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
//			File file = new File("BonafideSc1Temp.docx");
//			doc.write(new FileOutputStream(file));
//			if (file.exists()) {
//				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//				if (mimeType == null) {
//					mimeType = "application/octet-stream";
//				}
//				response.setContentType(mimeType);
//				response.setContentLength((int) file.length());
//				response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//						"attachment; filename=\"" + registerNumber + "_" + new Date().toString() + ".docx" + "\"");
//				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//				FileCopyUtils.copy(inputStream, response.getOutputStream());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@GetMapping("/school1/bonafide/{registerNumber}")
	public void dashboard(@PathVariable(value = "registerNumber") String registerNumber, HttpServletResponse response) {
		School1_StudentEntity student = repository.findByRegisterNumber(registerNumber);
		try {
			ClassPathResource cpr = new ClassPathResource(env.getProperty("school1.bonafide_certificate.doc.filePath"));
			LocalDate dt = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yy");
			Docx docx = new Docx(cpr.getInputStream());
			docx.setVariablePattern(new VariablePattern("#{", "}"));
			List<String> findVariables = docx.findVariables();
			for (String var : findVariables) {
				System.out.println("VARIABLE => " + var);
			}
			Variables var = new Variables();
			var.addTextVariable(new TextVariable("#{date}", formatter.format(dt)));
			var.addTextVariable(new TextVariable("#{name}",
					student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName()));
			var.addTextVariable(new TextVariable("#{standard}", student.getClassIn()));
			var.addTextVariable(new TextVariable("#{year}", "2020"));
			SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
			String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
			var.addTextVariable(new TextVariable("#{dob}", reformattedStr));
			var.addTextVariable(new TextVariable("#{cast}", student.getCaste()));
			var.addTextVariable(new TextVariable("#{reg}", student.getRegisterNumber()));
			docx.fillTemplate(var);

			File file = new File("BonafideSc1Temp.docx");
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
