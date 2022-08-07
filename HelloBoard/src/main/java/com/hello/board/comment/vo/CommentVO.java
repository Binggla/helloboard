package com.hello.board.comment.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentVO {
	private String postId;
	private int id;
	private String memberId;
	private String memberName;
	private Date enrollDate;
	private String contents;
	private int depth;
	private int group;
	private int deleted;
}
