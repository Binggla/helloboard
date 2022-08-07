package com.hello.board.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.board.comment.mapper.CommentMapper;
import com.hello.board.comment.service.CommentService;
import com.hello.board.comment.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired CommentMapper map;

	@Override
	public CommentVO commentSelect(CommentVO vo) {
		return map.commentSelect(vo);
	}

	@Override
	public int commentInsert(CommentVO vo) {
		return map.commentInsert(vo);
	}

	@Override
	public int commentUpdate(CommentVO vo) {
		return map.commentUpdate(vo);
	}

	@Override
	public int commentDelete(CommentVO vo) {
		return map.commentDelete(vo);
	}

}
