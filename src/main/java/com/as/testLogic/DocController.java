package com.as.testLogic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

@RestController
public class DocController {

	@Autowired
	Environment env;

	@GetMapping("getBonafideCertificate")
	public void getBonafideCertificate(HttpServletResponse response) throws IOException, DocumentException {
		response.setContentType("application/pdf;charset=UTF-8");
		response.addHeader("Content-Disposition", "inline; filename=" + "print1.pdf");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String FONT = env.getProperty("gujrati.font.filePath");
		PdfDocument pdfDoc = new PdfDocument(
				new PdfReader(env.getProperty("gujrati.bonafide_certificate.pdf.filePath")), new PdfWriter(baos));
		PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
		form.setGenerateAppearance(true);
		PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
		form.getField("srno").setValue("12345", font, 12f);
		form.getField("date").setValue(new Date().toString(), font, 12f);
		form.getField("name").setValue("સાહિલ શરદ પાટિલ", font, 12f);
		form.getField("year").setValue("2020", font, 12f);
		form.getField("cast").setValue("હિન્દુ - અગારી", font, 12f);
		form.getField("standard").setValue("12 HSC", font, 12f);
		form.getField("dob").setValue("25-કુચ-1998", font, 12f);
		form.getField("registerNumber").setValue("PRN223342342342", font, 12f);
		pdfDoc.close();
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
	}
}
