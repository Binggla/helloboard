package com.hello.board.post.service;

import java.util.List;

import com.hello.board.common.SearchVO;
import com.hello.board.post.vo.PostVO;

public interface PostService {
	
	List<PostVO> postSelectList(SearchVO vo);
	int getPostListCnt(String key);
	PostVO postSelect(PostVO vo);
	int postInsert(PostVO vo);
	int postUpdate(PostVO vo);
	int postDelete(PostVO vo);
	
	int postHitUpdate(int id);

}
