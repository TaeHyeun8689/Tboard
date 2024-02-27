package com.springproj.tboard.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproj.tboard.domain.ReplyVO;
import com.springproj.tboard.repository.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDao;

	@Override
	public List<ReplyVO> replyListPageCriteria(HashMap<String, Object> reply_params) throws Exception {
		return replyDao.replyListPageCriteria(reply_params);
	}

	@Override
	public Integer replyTotalCount(int board_num) throws Exception {
		return replyDao.replyTotalCount(board_num);
	}

	@Override
	public void replyWrite(HashMap<String, Object> params) throws Exception {
		replyDao.replyWrite(params);
	}

	@Override
	public void replyDelete(int reply_num) throws Exception {
		replyDao.replyDelete(reply_num);
	}
	
	@Override
	// 글 삭제 전 상속 댓글 삭제
	public void boardfirstDelete(HashMap<String, Object> params) throws Exception {
		replyDao.boardfirstDelete(params);
	}

	@Override
	public void replyUpdate(HashMap<String, Object> params) throws Exception {
		replyDao.replyUpdate(params);
	}
}
