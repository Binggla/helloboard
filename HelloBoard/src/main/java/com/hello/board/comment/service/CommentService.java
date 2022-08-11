package com.hello.board.comment.service;

import com.hello.board.comment.vo.CommentVO;

public interface CommentService {
	CommentVO commentSelect(CommentVO vo);
	int commentInsert(CommentVO vo);
	int commentUpdate(CommentVO vo);
	int commentDelete(CommentVO vo);
}
