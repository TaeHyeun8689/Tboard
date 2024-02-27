package com.springproj.tboard.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproj.tboard.domain.BoardVO;
import com.springproj.tboard.paging.PageCriteria;

@Mapper
public interface BoardDAO { // 전체 글 가져오기 및 페이징 처리를 위한 메서드 
	public List<BoardVO> listPageCriteria(PageCriteria pc) throws Exception;

//전체 게시글 수를 구하는 메서드
	public Integer totalCount() throws Exception;

// 게시글 내용을 보는 메서드 
	public BoardVO boardRead(HashMap<String, Object> params) throws Exception;

// 조회수 올리기 메서드
	public void countHit(int board_num) throws Exception;

// 글쓰기 메서드
	public void boardWrite(HashMap<String, Object> params) throws Exception;

// 글수정 메서드 
	public void boardUpdate(HashMap<String, Object> params) throws Exception;

//글삭제 메서드
	public void boardDelete(HashMap<String, Object> params) throws Exception;

// 댓글 수 세기 메서드
	public void countReply(int board_num) throws Exception;

// 검색 결과를 가져오는 메서드
	public List<BoardVO> searchBoard(HashMap<String, Object> search_params) throws Exception;

//검색 결과의 개수를 가져오는 메서드
	public Integer searchTotalCount(HashMap<String, Object> params) throws Exception;
	
// 추천수 올리기 메서드
	public void countRecommned(HashMap<String, Object> params) throws Exception;
}