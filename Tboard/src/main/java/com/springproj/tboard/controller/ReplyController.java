package com.springproj.tboard.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproj.tboard.service.BoardService;
import com.springproj.tboard.service.ReplyService;



@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@Autowired
	private BoardService boardService;

	// GET replyWrite
	@RequestMapping("board/view/replyWrite")
	public String responseReplyWrite(@RequestParam HashMap<String, Object> params) throws Exception {

		// 댓글 작성
		replyService.replyWrite(params);
		// 댓글 작성시 마다 board의 컬럼 board_reply_count에 댓글 개수 업데이트
		System.out.println("댓글 작성 countReply 업데이트");
		System.out.println(params.get("board_num"));
		boardService.countReply(Integer.parseInt((String) params.get("board_num")));
		
		
		return "redirect:/board/view?board_num=" + params.get("board_num") + "&page=" + params.get("page") + "&perPageNum=" + params.get("perPageNum");
	}

	@RequestMapping("board/view/replyDelete")
	public String responseReplyDelete(@RequestParam HashMap<String, Object> params) throws Exception {

		// 댓글 삭제
		replyService.replyDelete(Integer.parseInt((String) params.get("reply_index")));
		// 댓글 삭제시 마다 board의 컬럼 board_reply_count에 댓글 개수 업데이트
		boardService.countReply(Integer.parseInt((String) params.get("board_num")));

		return "redirect:/board/view?board_num=" + params.get("board_num") + "&page=" + params.get("page") + "&perPageNum=" + params.get("perPageNum");
	}

	@RequestMapping("board/view/replyUpdate")
	public String responseReplyUpdate(@RequestParam HashMap<String, Object> params) throws Exception {

		// 댓글 수정
		System.out.println(params);
		replyService.replyUpdate(params);

		return "redirect:/board/view?board_num=" + params.get("board_num") + "&page=" + params.get("page") + "&perPageNum=" + params.get("perPageNum");
	}
}