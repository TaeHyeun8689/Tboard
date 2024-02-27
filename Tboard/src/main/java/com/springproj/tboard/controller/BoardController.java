package com.springproj.tboard.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproj.tboard.domain.BoardVO;
import com.springproj.tboard.domain.ReplyVO;
import com.springproj.tboard.domain.UserVO;
import com.springproj.tboard.paging.PageCriteria;
import com.springproj.tboard.paging.PageMaker;
import com.springproj.tboard.paging.ReplyPageCriteria;
import com.springproj.tboard.paging.ReplyPageMaker;
import com.springproj.tboard.service.BoardService;
import com.springproj.tboard.service.ReplyService;
import com.springproj.tboard.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;

	@Autowired
	private UserService userService;

//메인 화면 호출 //pc에 담기는 파라미터 page, perPageNum

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String responseIndex(@ModelAttribute("pc") PageCriteria pc, Model model) throws Exception {

		System.out.println("pc안에 담긴 값 : " + pc);

		List<BoardVO> vo = boardService.listPageCriteria(pc);
		System.out.println("boardvo 에 담긴 값 : " + vo);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setPc(pc);
		Integer totalPageNum = boardService.totalCount();
		pageMaker.setTotalCount(totalPageNum);

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", vo);

		return "index";

	}

	// 글 내용 보기
	// params에 담기는 파라미터 page, perPageNum, b_num
	// rpc에 담기는 파라미터 replyPage, replyPerPageNum
	@RequestMapping("board/view")
	public String responseRead(ReplyPageCriteria rpc, @RequestParam HashMap<String, Object> params, Model model,
			HttpSession session) throws Exception {

		// 글 내용 가져오기
		System.out.println("글 내용 가져오기 메서드 실행");

		BoardVO bvo = boardService.boardRead(params);
		// 글 조회수 올리기
		System.out.println(bvo.getBoard_num());
		boardService.countHit(bvo.getBoard_num());

		int board_num = Integer.parseInt((String) params.get("board_num"));

		HashMap<String, Object> reply_params = new HashMap<String, Object>();
		reply_params.put("board_num", board_num);
		reply_params.put("replyPageStart", rpc.getReplyPageStart());
		reply_params.put("replyPerPageNum", rpc.getReplyPerPageNum());

		// 댓글 리스트 불러오기
		System.out.println("댓글 리스트 불러오기");
		List<ReplyVO> rvo = replyService.replyListPageCriteria(reply_params);

		// 댓글 페이징
		ReplyPageMaker replyPageMaker = new ReplyPageMaker();
		replyPageMaker.setRpc(rpc);
		System.out.println("댓글 페이징 메서드 불러오기");
		Integer replyTotalPageNum = replyService.replyTotalCount(board_num);
		replyPageMaker.setReplyTotalCount(replyTotalPageNum);

		// 현재 세션 로그인 유저 아이디 가져오기
		UserVO uvo = (UserVO) session.getAttribute("login_session");
		// 유저 추천 활성화 시간 조회, "board/recommend" 요청 결과 값을 view.jsp hidden값(u_r_a_t) 갱신을 위해
		// 조회하여 model에 추가한다
		System.out.println("유저 추천 활성화 시간 조회 메서드");
		if (uvo != null) {
			Timestamp user_recommend_active = userService.checkRecommendActiveTime(uvo.getUser_id());
			model.addAttribute("user_recommend_active_time", user_recommend_active);
		}

		// 각 객체에 데이터 담아서 view.jsp 페이지로 반환
		System.out.println("bvo 출력 : " + bvo);
		model.addAttribute("replyPageMaker", replyPageMaker);
		model.addAttribute("replyList", rvo);
		model.addAttribute("v_content", bvo);
		model.addAttribute("page", params.get("page"));
		model.addAttribute("perPageNum", params.get("perPageNum"));

		return "board/view";
	}

	// 글쓰기 페이지로 이동
	@RequestMapping("board/writeForm")
	public String responseMoveWriteForm(HttpSession session) {
	    if (session.getAttribute("login_session") == null) {
	        // 로그인 세션이 없는 경우
	        return "redirect:/index"; // 로그인 페이지로 리다이렉트
	    }
	    // 로그인 세션이 있는 경우 글쓰기 페이지로 이동
	    return "board/writeForm";
	}

	// 글쓰기
	@RequestMapping("board/write")
	public String responseWrite(@RequestParam HashMap<String, Object> params) throws Exception {

		boardService.boardWrite(params);

		return "redirect:/index";

	}

	// 글수정 페이지로 이동(+b_num에 맞는 글내용 조회)
	@RequestMapping("board/updateForm")
	public String responseMoveUpdateForm(@RequestParam HashMap<String, Object> params, Model model, HttpSession session)
			throws Exception {

		// 수정할 글 세부 정보 가져오기
		BoardVO bvo = boardService.boardRead(params);

		// 현재 세션 로그인 유저 아이디 가져오기
		UserVO uvo = (UserVO) session.getAttribute("login_session");

		// 현재 세션 로그인 유저 equals 선택 글 작성자인 경우 수정 페이지로 이동
		// ==는 객체의 참조 주소값을 비교하는 연산자
		// equals()는 객체의 내용 값을 비교하는 메소드
		if (bvo.getBoard_writer().equals(uvo.getUser_id())) {
			model.addAttribute("user_content", bvo);
			model.addAttribute("page", params.get("page"));
			model.addAttribute("perPageNum", params.get("perPageNum"));
			return "board/updateForm";
		} else {
			JOptionPane.showMessageDialog(null, "수정할 수 없는 글입니다");
			// 다시 보고 있던 글로 리턴
			return "redirect:view?board_num=" + params.get("board_num") + "&page=" + params.get("page") + "&perPageNum="
					+ params.get("perPageNum");
		}

	}

	// 글수정
	// 글수정시 자동으로 등록날짜 업데이트
	@RequestMapping("board/update")
	public String responseUpdate(@RequestParam HashMap<String, Object> params) throws Exception {

		boardService.boardUpdate(params);

		return "redirect:/index";
	}

	// 글삭제
	@RequestMapping("board/delete")
	public String responseDelete(@RequestParam HashMap<String, Object> params) throws Exception {
		System.out.println("글 삭제 파라미터: " + params);
		
		replyService.boardfirstDelete(params);

		boardService.boardDelete(params);

		return "redirect:/index";
	}

	// 검색
	@RequestMapping("/search")
	public String responseSearch(@ModelAttribute("pc") PageCriteria pc, @RequestParam HashMap<String, Object> params,
			Model model) throws Exception {
		// 검색결과 20개씩 뿌리기
		pc.setPerPageNum(20);

		// 파라미터 합치기
		HashMap<String, Object> search_params = new HashMap<String, Object>();
		search_params.put("search_condition", params.get("search_condition"));
		search_params.put("search_content", params.get("search_content"));
		search_params.put("pageStart", pc.getPageStart());
		search_params.put("perPageNum", pc.getPerPageNum());
		System.out.println("search_params 값: " + search_params);

		// 검색 리스트 부르기
		List<BoardVO> bvo = boardService.searchBoard(search_params);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setPc(pc);
		// 검색한 결과값의 개수 구하기
		System.out.println(params);
		Integer totalPageNum = boardService.searchTotalCount(params);
		pageMaker.setTotalCount(totalPageNum);

		// 모델에 값 담기
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", bvo);
		model.addAttribute("search_condition", params.get("search_condition"));
		model.addAttribute("search_content", params.get("search_content"));

		// 검색했던 결과값 유지해야 페이징 기능 이용이 가능하다
		return "search";
	}

	// 추천하기
	@RequestMapping("board/recommend")
	public String responseRecommned(@RequestParam HashMap<String, Object> params) throws Exception {
		// 현재시간 > u_recommend_active_time 인 경우, 추천Go
		System.out.println("추천하기 메서드 실행");
		boardService.countRecommned(params);
		// 추천 후, 유저 u_recommend_active_time에 현재시간+1분(시간 변경가능, board_mapper) 업데이트
		userService.updateRecommendActiveTime((String) params.get("user_id"));

		return "redirect:view?board_num=" + params.get("board_num") + "&page=" + params.get("page") + "&perPageNum="
				+ params.get("perPageNum");
	}

}
