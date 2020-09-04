package com.as.DocGenegatr;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


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
	
	@GetMapping("getPdf")
	public void exportToPDF(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		try {
			Data data = new Data("8596582", "Sahil Patil", "03/8/2020", "$2,000.00", "1005205", "452562");
			StringTemplate page = getStringTemplate();
			page.setAttribute("data", data);
			String content = page.toString();
			final HtmlCleaner htmlCleaner = new HtmlCleaner();
			final TagNode tagNode = htmlCleaner.clean(content);
			content = htmlCleaner.getInnerHtml(tagNode);
			Document document = new Document();
			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			StringBuilder htmlString = new StringBuilder();
			htmlString.append(content);
			document.open();
			InputStream is = new ByteArrayInputStream(htmlString.toString().getBytes("utf-8"));
			Reader targetReader = new InputStreamReader(is, StandardCharsets.UTF_8);
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, targetReader);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	StringTemplate getStringTemplate() {
		final StringTemplateGroup group = new StringTemplateGroup("Generators");
		return group.getInstanceOf("employee_details");
	}
}
