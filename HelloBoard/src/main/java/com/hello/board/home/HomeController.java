package com.hello.board.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hello.board.common.PagingVO;
import com.hello.board.common.SearchVO;
import com.hello.board.post.service.PostService;

@Controller
public class HomeController {
	
	String defaultPath = "/page/post/";
	@Autowired PostService postService;
	
	@GetMapping("/")
	public String home(Model model, 
						SearchVO svo,
						@RequestParam(required = false, defaultValue = "1") int page,
			            @RequestParam(required = false, defaultValue = "1") int range) {
		svo.setState(1);
		
		PagingVO pvo = new PagingVO();
		pvo.pageInfo(page, range, postService.getPostListCnt(null), 5, 10);
		
		model.addAttribute("postList", postService.postSelectList(svo, pvo));
		model.addAttribute("paging", pvo);
		return defaultPath + "postList";
	}
	
	@GetMapping("/home")
	public String homepage(Model model, SearchVO vo) {
		vo.setState(1);
		//model.addAttribute("postList", postService.postSelectList(vo));
		return defaultPath + "postList";
	}
}
