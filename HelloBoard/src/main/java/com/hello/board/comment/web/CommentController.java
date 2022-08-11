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
		String msg = "";
		
		if (job.equals("insert")) {
			
			int result = commentService.commentInsert(vo);
			if (result > 0) {
				msg = "등록이 완료되었습니다.";
			} else {
				msg = "등록이 정상적으로 처리되지 않았습니다.";
			}
			
		} else if (job.equals("delete")) {
			
			int result = commentService.commentDelete(vo);
			if (result < 0) {
				msg = "삭제가 완료되었습니다.";
			} else {
				msg = "삭제가 정상적으로 처리되지 않았습니다.";
			}
			
		} else if (job.equals("update")) {
			
			int result = commentService.commentUpdate(vo);
			if(result > 0) {
				msg = "수정이 완료되었습니다.";
			} else {
				msg = "수정이 정상적으로 처리되지 않았습니다.";
			}
			
		} else {
			msg = "유효하지 않은 접근입니다.";
		}
		
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/post?id=" + vo.getPostId();
	}
}
