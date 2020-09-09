package com.as.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.as.entity.StudentEntity;
import com.as.repository.StudentRepository;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

@Controller
@RequestMapping("bonafide")
public class BonafideController {

	@Autowired
	Environment env;

	@Autowired
	StudentRepository repository;

	@GetMapping("")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("bonafide");
		modelAndView.addObject("pageTitle", "Get Bonafide");
		return modelAndView;
	}

	@GetMapping("/bonafide/{registerNumber}")
	public void getBonafideCertificate(@PathVariable(value = "registerNumber") String registerNumber,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, DocumentException, ParseException {
		System.out.println(registerNumber);
		StudentEntity student = repository.findByRegisterNumber(registerNumber);

		response.setContentType("application/pdf;charset=UTF-8");
		response.addHeader("Content-Disposition", "inline; filename=" + "print1.pdf");
//		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "print1.pdf" + "\"");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String FONT = env.getProperty("gujrati.font.filePath");
		PdfDocument pdfDoc = new PdfDocument(
				new PdfReader(env.getProperty("gujrati.bonafide_certificate.pdf.filePath")), new PdfWriter(baos));
		PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
		form.setGenerateAppearance(true);
		PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
		form.getField("srno").setValue("1234", font, 12f);
		form.getField("date").setValue(new Date().toString(), font, 12f);
		form.getField("name").setValue(
				student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName(), font, 12f);
		form.getField("year").setValue("2020", font, 12f);
		form.getField("cast").setValue(student.getCaste(), font, 12f);
		form.getField("standard").setValue("12 HSC", font, 12f);

		SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
		String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
		form.getField("dob").setValue(reformattedStr, font, 12f);

		form.getField("registerNumber").setValue(student.getRegisterNumber(), font, 12f);
		pdfDoc.close();
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
	}

}
