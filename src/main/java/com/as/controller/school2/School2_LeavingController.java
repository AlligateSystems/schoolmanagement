package com.as.controller.school2;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class School2_LeavingController {

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping("school2/leaving")
	public void downloadSampleCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:documents/school2/School2_Leaving_Document.docx");
		if (resource.exists()) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", String.format("attachment; filename=" + resource.getFilename()));
			response.setContentLength((int) resource.contentLength());
			InputStream inputStream = resource.getInputStream();
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
}
