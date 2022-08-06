package com.hello.board.comment.mapper;

import java.util.List;

import com.hello.board.comment.vo.CommentVO;
import com.hello.board.post.vo.PostVO;

public interface CommentMapper {
	List<CommentVO> commentListSelect(int id);
}
