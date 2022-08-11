package com.hello.board.common.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
	String fileDir = "/Users/binggla/Desktop/temp/";
	
	// 파일 다운로드
	@RequestMapping("/download")
	public ResponseEntity<Object> download(
			@Param("fileName") String fileName,
			@Param("originName") String originName,
			HttpServletResponse response) throws FileNotFoundException {
		
		String path = fileDir + fileName;
		
		try {
			Path filePath = Paths.get(path);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(originName).build());
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
		
	}
	
	// 이미지 파일 가져오기
	@RequestMapping("/display")
	public ResponseEntity<Resource> display(
			@Param("fileName") String fileName) {
		
		String file = fileDir + File.separator + fileName;
		
		Resource resource = new FileSystemResource(file);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
			
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		
		try {
			filePath = Paths.get(file);
			header.add("Content-Type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
		
	}
}
