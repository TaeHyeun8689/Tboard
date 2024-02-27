package com.springproj.tboard.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproj.tboard.domain.BoardVO;
import com.springproj.tboard.paging.PageCriteria;
import com.springproj.tboard.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	@Override
	public List<BoardVO> listPageCriteria(PageCriteria pc) throws Exception {
		return boardDao.listPageCriteria(pc);
	}

	@Override
	public Integer totalCount() throws Exception {
		return boardDao.totalCount();
	}

	@Override
	public BoardVO boardRead(HashMap<String, Object> params) throws Exception {
		return boardDao.boardRead(params);
	}

	@Override
	public void countHit(int board_num) throws Exception {
		boardDao.countHit(board_num);
	}

	@Override
	public void boardWrite(HashMap<String, Object> params) throws Exception {
		boardDao.boardWrite(params);
	}

	@Override
	public void boardUpdate(HashMap<String, Object> params) throws Exception {
		boardDao.boardUpdate(params);
	}

	@Override
	public void boardDelete(HashMap<String, Object> params) throws Exception {
		boardDao.boardDelete(params);
	}

	@Override
	public void countReply(int board_num) throws Exception {
		boardDao.countReply(board_num);
	}

	@Override
	public List<BoardVO> searchBoard(HashMap<String, Object> search_params) throws Exception {
		return boardDao.searchBoard(search_params);
	}

	@Override
	public Integer searchTotalCount(HashMap<String, Object> params) throws Exception {
		return boardDao.searchTotalCount(params);
	}

	@Override
	public void countRecommned(HashMap<String, Object> params) throws Exception {
		boardDao.countRecommned(params);
	}
}
