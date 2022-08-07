package com.hello.board.comment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hello.board.comment.vo.CommentVO;

@Mapper
public interface CommentMapper {
	CommentVO commentSelect(CommentVO vo);
	int commentInsert(CommentVO vo);
	int commentUpdate(CommentVO vo);
	int commentDelete(CommentVO vo);
}
