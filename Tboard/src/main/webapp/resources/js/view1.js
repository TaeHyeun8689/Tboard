$(document).ready(function() {

	// 추천하기
	$("#recommendSubmit").click(function() {
		if (confirm("추천을 하시겠습니까?")) {
			if ($("#user_id").val() != null) {
				$("#recommend").submit();
			} else {
				// 로그인된 아이디가 없는 경우 인터셉터 경로로 반환
				alert("로그인 시 댓글 작성이 가능합니다.")
				window.location = "index";
			}
		}
	});

	// 댓글 쓰기
	$("#replyWriteSubmit").click(function() {
		if (confirm("댓글 작성을 완료하시겠습니까?")) {
			$("#replyWrite").submit();
		} else {
			return;
		}
	});

});

// 댓글 삭제하기
// reply_index(rIndex): 댓글 고유 번호 값을 파라미터로 가져온 후 컨트롤러로 전송한다
// b_num, page, perPageNum은 view 페이지를 불러오기 위한 파라미터
function replyDeleteFunc(rIndex) {
	if (confirm("댓글을 삭제하시겠습니까?")) {
		window.location = "view/replyDelete?reply_index=" + rIndex + "&board_num="
				+ $("#hidden_board_num").val() + "&page=" + $("#hidden_page").val()
				+ "&perPageNum=" + $("#hidden_perPageNum").val();
	} else {
		return;
	}
}

// 댓글 수정하기
// reply_content_rIndex: 댓글 고유 번호 파라미터를 이용해서 reply_content에 고유 id를 설정
// ex) rIndex = 15 >> #reply_content_15
// get 방식으로 replyUpdate 요청
function replyUpdateFunc(rIndex) {
	if (confirm("댓글을 수정하시겠습니까?")) {
		window.location = "view/replyUpdate?reply_num=" + rIndex
				+ "&reply_content=" + $("#reply_content_" + rIndex).val()
				+ "&board_num=" + $("#hidden_board_num").val() + "&page="
				+ $("#hidden_page").val() + "&perPageNum="
				+ $("#hidden_perPageNum").val();
	} else {
		return;
	}
}