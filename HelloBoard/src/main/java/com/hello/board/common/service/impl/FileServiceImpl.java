package com.hello.board.common.service.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hello.board.common.service.FileService;

/**
 * @brief 파일 업로드, 삭제
 * @author 지수빈
 * @date 2022/07/13
 * @details
 * 1. upload() - 파일 업로드
 *   parameter : MultipartFile file(파일 목록 배열), 
 *   			 String folder(WEB-INF/files/* 폴더명)
 *   return : String[] 
 *   		  [originName(실제 파일명), fileName(변환되어 저장된 파일명)]
 * 2. delete() - 파일 삭제
 *   parameter : String fileName(변환되어 저장된 파일명), 
 *   			 String folder(WEB-INF/files/* 폴더명)
 *   
 * Controller에서 '@Autowired FileService 변수명'으로 주입하여 메서드 사용
 */

@Service
public class FileServiceImpl implements FileService {
	
	String fileDir = "/Users/binggla/Desktop/temp/";
	
	@Override
	public String[] upload(MultipartFile file) {
		
		String saveDir = fileDir;
		File dir = new File(saveDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String[] fileInfo = new String[2];
		
		String originName = file.getOriginalFilename();
		String uid = UUID.randomUUID().toString();
		String fileName = uid + originName.substring(originName.indexOf("."));
		
		File target = new File(saveDir,fileName);
		
		fileInfo[0] = originName;
		fileInfo[1] = fileName;
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return fileInfo;

	}

	@Override
	public void delete(String fileName) {
		
		String filePath = fileDir + File.separator + fileName;
		
		File file = new File(filePath);
		file.delete();

	}

}
