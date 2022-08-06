package com.hello.board.post.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.board.common.SearchVO;
import com.hello.board.post.mapper.PostMapper;
import com.hello.board.post.service.PostService;
import com.hello.board.post.vo.PostVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired PostMapper map;

	@Override
	public List<PostVO> postSelectList(SearchVO vo) {
		return map.postSelectList(vo);
	}

	@Override
	public int getPostListCnt(String key) {
		return map.getPostListCnt(key);
	}

	@Override
	public PostVO postSelect(PostVO vo) {
		return map.postSelect(vo);
	}

	@Override
	public int postInsert(PostVO vo) {
		return map.postInsert(vo);
	}

	@Override
	public int postUpdate(PostVO vo) {
		return map.postUpdate(vo);
	}

	@Override
	public int postDelete(PostVO vo) {
		return map.postDelete(vo);
	}

	@Override
	public int postHitUpdate(int id) {
		return map.postHitUpdate(id);
	}

}
