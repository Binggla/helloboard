package com.hello.board.post.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hello.board.common.service.FileService;
import com.hello.board.post.service.PostService;
import com.hello.board.post.vo.PostVO;

@Controller
public class PostController {

	String defaultPath = "page/post/";
	@Autowired PostService postService;
	@Autowired FileService fileService;
	
	@GetMapping("/post")
	public String postSelect(PostVO vo, Model model) {
		postService.postHitUpdate(vo.getId());
		model.addAttribute("post", postService.postSelect(vo));
		return defaultPath + "postSelect";
	}
	
	@GetMapping("/post/{job}")
	public String postFrm(@PathVariable String job) {
		
		String path = "";
		
		if (job.equals("insert")) {
			path = "postInsert";
		}
		
		return defaultPath + path;
	}
	
	@PostMapping("/post/{job}")
	public String postInsert(@PathVariable String job, 
							 RedirectAttributes ra,
							 PostVO vo,
							 MultipartFile file) {
		
		String msg = "";
		String fileName = file.getOriginalFilename();
		
		if (job.equals("insert")) {
			
			if (fileName != null && !fileName.isEmpty() && fileName.length() != 0) {
				String[] fileInfo = fileService.upload(file);
				vo.setOriginFileName(fileInfo[0]);
				vo.setFileName(fileInfo[1]);
			}
			
			vo.setEnrollDate(new Date(System.currentTimeMillis()));
			
			int result = postService.postInsert(vo);
			if (result > 0) {
				msg = "등록이 완료되었습니다.";
			} else {
				msg = "등록이 정상적으로 처리되지 않았습니다.";
			}
		}
		
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/home";
	}
	
}
