package com.as.DocGenegatr;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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

	@GetMapping("getDoc")
	public void dashboard(HttpServletResponse response) {

		try {
			XWPFDocument doc = new XWPFDocument(OPCPackage
					.open(ResourceUtils.getFile(env.getProperty("gujrati.document.filePath"))));
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
					if (text != null && text.contains("$studentname$")) {
						text = text.replace("$studentname$", "SahilSharadPatil");
						r.setText(text, 0);
					}
					if (text != null && text.contains("$standard$")) {
						text = text.replace("$standard$", "Ninth_th");
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
					if (text != null && text.contains("$registrationnumber$")) {
						text = text.replace("$registrationnumber$", "PRN1224242");
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
