package com.hello.board.comment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hello.board.comment.service.CommentService;
import com.hello.board.comment.vo.CommentVO;

@Controller
public class CommentController {
	
	@Autowired CommentService commentService;

	@PostMapping("/comment/{job}")
	public String commentJob(@PathVariable String job, 
							 RedirectAttributes ra,
							 CommentVO vo) {
		System.out.println(vo);
		String msg = "";
		
		if (job.equals("insert")) {
			int result = commentService.commentInsert(vo);
			if (result > 0) {
				msg = "등록이 완료되었습니다.";
			} else {
				msg = "등록이 정상적으로 처리되지 않았습니다.";
			}
		}
		
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/post?id=" + vo.getPostId();
	}
}
