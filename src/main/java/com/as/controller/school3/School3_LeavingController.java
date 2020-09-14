package com.as.controller.school3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class School3_LeavingController {

	@Autowired
	Environment env;

//	@Autowired
//	School3_StudentRepository repository;
	
	@RequestMapping("school3/leaving")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
			File file = new File(env.getProperty("school3.leaving_certificate.doc.filePath"));
			if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
	
	
//	@GetMapping("/school3/leaving")
//	public ModelAndView attendance() {
//		ModelAndView modelAndView = new ModelAndView("school3/leaving");
//		modelAndView.addObject("pageTitle", "Get Leaving");
//		return modelAndView;
//	}
	
//	@GetMapping("/school3/leaving/{registerNumber}")
//	public void dashboard(@PathVariable(value = "registerNumber") String registerNumber, HttpServletResponse response) {
//		School3_StudentEntity student = repository.findByRegisterNumber(registerNumber);
//		try {
//			XWPFDocument doc = new XWPFDocument(OPCPackage
//					.open(ResourceUtils.getFile(env.getProperty("school3.leaving_certificate.doc.filePath"))));
//			
//			/*
//			 * For Table
//			 */
//			for (XWPFTable tbl : doc.getTables()) {
//				for (XWPFTableRow row : tbl.getRows()) {
//					for (XWPFTableCell cell : row.getTableCells()) {
//						for (XWPFParagraph p : cell.getParagraphs()) {
//							for (XWPFRun r : p.getRuns()) {
//								String text = r.getText(0);
//								System.out.println(text);
//								if (text != null && text.contains("$name$")) {
//									text = text.replace("$name$",
//											student.getLastName() + " " + student.getFirstName() + " " + student.getMiddleName());
//									r.setText(text, 0);
//								}
//								if (text != null && text.contains("$dob$")) {
//									SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
//									SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
//									String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
//									text = text.replace("$dob$", reformattedStr);
//									r.setText(text, 0);
//								}
//								
//								if (text != null && text.contains("$dow$")) {
//									SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
//									SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
//									String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
//									text = text.replace("$dow$", Common.DateToWordsInput(reformattedStr));
//									r.setText(text, 0);
//								}
//								
//								if (text != null && text.contains("$cast$")) {
//									text = text.replace("$cast$", student.getCaste());
//									r.setText(text, 0);
//								}
//								if (text != null && text.contains("$place$")) {
//									text = text.replace("$place$", student.getBirthPlace());
//									r.setText(text, 0);
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			for (XWPFParagraph p : doc.getParagraphs()) {
//				for (XWPFRun r : p.getRuns()) {
//					String text = r.getText(0);
//					if (text != null && text.contains("$RN$")) {
//						text = text.replace("$RN$", student.getRegisterNumber());
//						r.setText(text, 0);
//					}
//					r.setText(text, 0);
//				}
//			}
////			doc.write(new FileOutputStream(new File("c://createdocument.docx")));
//			File file = new File("LeavingTemp.docx");
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
}
