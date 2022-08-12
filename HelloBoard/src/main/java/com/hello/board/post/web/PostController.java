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
import com.hello.board.common.vo.FileVO;
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
	public String postFrm(@PathVariable String job, PostVO vo, Model model) {
		
		String path = "";
		
		if (job.equals("insert")) {
			path = "postInsert";
		} else if (job.equals("update")) {
			model.addAttribute("post", postService.postSelect(vo));
			path = "postUpdate";
		}
		
		return defaultPath + path;
	}
	
	@PostMapping("/post/{job}")
	public String postJob(@PathVariable String job, 
							 RedirectAttributes ra,
							 PostVO vo,
							 String prevFile,
							 MultipartFile file) {
		
		String msg = "";
		int newFileCnt = 0;
		
		if (file != null) {
			if (!file.isEmpty()) {
				newFileCnt++;
				FileVO fvo = fileService.upload(file);
				vo.setOriginFileName(fvo.getOriginFileName());
				vo.setFileName(fvo.getFileName());
			}
		}
	
		if (job.equals("insert")) {
			
			vo.setEnrollDate(new Date(System.currentTimeMillis()));
			
			int result = postService.postInsert(vo);
			if (result > 0) {
				msg = "등록이 완료되었습니다.";
			} else {
				msg = "등록이 정상적으로 처리되지 않았습니다.";
			}
			
		} else if (job.equals("update")) {
			
			if (newFileCnt > 0) {
				fileService.delete(prevFile);
			}
			System.out.println(vo);

			int result = postService.postUpdate(vo);
			if (result > 0) {
				msg = "수정이 완료되었습니다.";
			} else {
				msg = "수정이 정상적으로 처리되지 않았습니다.";
				
			}
			
		} else if (job.equals("delete")) {
			
			fileService.delete(prevFile);
			
			int result = postService.postDelete(vo);
			if (result > 0) {
				msg = "삭제가 완료되었습니다.";
			} else {
				msg = "삭제가 정상적으로 처리되지 않았습니다.";
			}
		}
		
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/home";
	}
	
}
