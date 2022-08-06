package com.hello.board.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hello.board.common.SearchVO;
import com.hello.board.post.vo.PostVO;

@Mapper
public interface PostMapper {
	
	List<PostVO> postSelectList(SearchVO vo);
	int getPostListCnt(String key);
	PostVO postSelect(PostVO vo);
	int postInsert(PostVO vo);
	int postUpdate(PostVO vo);
	int postDelete(PostVO vo);
	
	int postHitUpdate(int id);

}
