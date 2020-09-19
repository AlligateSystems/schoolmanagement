package com.as.controller.school1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.as.entity.school1.School1_StudentEntity;
import com.as.repository.school1.School1_StudentRepository;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

@Controller
public class School1_LeavingController {

	@Autowired
	Environment env;

	@Autowired
	School1_StudentRepository repository;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/school1/leaving")
	public ModelAndView attendance() {
		ModelAndView modelAndView = new ModelAndView("school1/leaving");
		modelAndView.addObject("pageTitle", "Get Leaving");
		return modelAndView;
	}

	@RequestMapping("school1/leaving/get")
	public void downloadSampleCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:documents/school1/School1_Leaving_Document.docx");
		if (resource.exists()) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", String.format("attachment; filename=" + resource.getFilename()));
			response.setContentLength((int) resource.contentLength());
			InputStream inputStream = resource.getInputStream();
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
	
	@GetMapping("/school1/leaving/{registerNumber}")
	public void dashboard(@PathVariable(value = "registerNumber") String registerNumber, HttpServletResponse response) {
		School1_StudentEntity student = repository.findByRegisterNumber(registerNumber);
		try {
			ClassPathResource cpr = new ClassPathResource(env.getProperty("school1.leaving_certificate.doc.filePath"));
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
			var.addTextVariable(new TextVariable("#{mother}", student.getMotherName()));
			var.addTextVariable(new TextVariable("#{home}", student.getBirthPlace()));
			SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
			String reformattedStr = toFormat.format(fromFormat.parse(student.getDateOfBirth()));
			var.addTextVariable(new TextVariable("#{dob}", reformattedStr));
			var.addTextVariable(new TextVariable("#{cast}", student.getCaste()));
			var.addTextVariable(new TextVariable("#{reg}", student.getRegisterNumber()));
			
		
			
			docx.fillTemplate(var);

			File file = new File("Temp_SC1_Leaving.docx");
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
