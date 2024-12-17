package com.gaocon.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gaocon.service.UserService;

@RestController
@RequestMapping(value="/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("/test")
	public void fileTest(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info(file.getName());
		logger.info(String.valueOf(file.getSize()));
		logger.info(file.getOriginalFilename());
		
		if(!file.isEmpty()){
			String fileName= file.getOriginalFilename();
			String filePath="C:\\test";
			
			FileUtils.copyInputStreamToFile(file.getInputStream()
					, new File(filePath, fileName));
		}
	}
	
}