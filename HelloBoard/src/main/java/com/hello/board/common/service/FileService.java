package com.hello.board.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	/**
	 * @brief 파일 업로드
	 * @param file
	 * @return originName(실제 파일명), fileName(변환되어 저장된 파일명)
	 */
	public String[] upload(MultipartFile file);
	public void delete(String fileName);
	
}
