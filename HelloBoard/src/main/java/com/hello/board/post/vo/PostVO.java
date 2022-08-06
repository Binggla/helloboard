package com.hello.board.post.vo;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hello.board.comment.vo.CommentVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	private int id;
	private String memberId;
	private String memberName;
	private String title;
	private String contents;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date enrollDate;
	private int hit;
	private String fileName;
	private String originFileName;
	
	private List<CommentVO> comments;
}
