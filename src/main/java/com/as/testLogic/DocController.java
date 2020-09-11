package com.as.testLogic;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DocController {

	@Autowired
	Environment env;

//	@GetMapping("getBonafideCertificate")
//	public void getBonafideCertificate(HttpServletResponse response) throws IOException, DocumentException {
//		response.setContentType("application/pdf;charset=UTF-8");
//		response.addHeader("Content-Disposition", "inline; filename=" + "print1.pdf");
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		String FONT = env.getProperty("gujrati.font.filePath");
//		PdfDocument pdfDoc = new PdfDocument(
//				new PdfReader(env.getProperty("gujrati.bonafide_certificate.pdf.filePath")), new PdfWriter(baos));
//		PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
//		form.setGenerateAppearance(true);
//		PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
//		form.getField("srno").setValue("12345", font, 12f);
//		form.getField("date").setValue(new Date().toString(), font, 12f);
//		form.getField("name").setValue("સાહિલ શરદ પાટિલ", font, 12f);
//		form.getField("year").setValue("2020", font, 12f);
//		form.getField("cast").setValue("હિન્દુ - અગારી", font, 12f);
//		form.getField("standard").setValue("12 HSC", font, 12f);
//		form.getField("dob").setValue("25-કુચ-1998", font, 12f);
//		form.getField("registerNumber").setValue("PRN223342342342", font, 12f);
//		pdfDoc.close();
//		ServletOutputStream out = response.getOutputStream();
//		baos.writeTo(out);
//		out.flush();
//	}

	@GetMapping("getDoc")
	public void dashboard(HttpServletResponse response) {

		try {
			XWPFDocument doc = new XWPFDocument(
					OPCPackage.open(ResourceUtils.getFile(env.getProperty("gujrati.bonafide_certificate.doc.filePath"))));
			LocalDate dt = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yy");
			for (XWPFParagraph p : doc.getParagraphs()) {
				for (XWPFRun r : p.getRuns()) {
					System.out.println(r);
					String text = r.getText(0);
					if (text != null && text.contains("$date$")) {
						text = text.replace("$date$", formatter.format(dt));
						r.setText(text, 0);
					}
					if (text != null && text.contains("$name$")) {
						text = text.replace("$name$", "SahilSharadPatil");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$standard$")) {
						text = text.replace("$standard$", "9th");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$year$")) {
						text = text.replace("$year$", "2020");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$dob$")) {
						text = text.replace("$dob$", "25_March_1998");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$cast$")) {
						text = text.replace("$cast$", "Agari");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$registerNumber$")) {
						text = text.replace("$registerNumber$", "PRN1224242");
						r.setText(text, 0);
					}
					r.setText(text, 0);
				}
			}
//			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
			File file = new File("createdocument.docx");
			doc.write(new FileOutputStream(file));

			if (file.exists()) {
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) file.length());
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
