$(document).ready(function() {
	// 로그인 정보 키 입력시 유효성 검사
	// 아이디
	$("#user_id").focus(function() {
		if ($("#id").val() == 0) {
			$("#idInputGroup").addClass("has-error");
		}
		$("#user_id").keyup(function() {
			if ($("#user_id").val().length >= 4) {
				$("#idInputGroup").removeClass("has-error");
				$("#idInputGroup").addClass("has-success");
				$("#idDupCheck").removeClass("disabled");
			} else {
				$("#idInputGroup").removeClass("has-success");
				$("#idInputGroup").addClass("has-error");
				$("#idDupCheck").addClass("disabled");
			}
		});
	});

	// 비밀번호
	$("#user_ps").focus(function() {
		if ($("#user_ps").val() == 0) {
			$("#pwInputGroup").addClass("has-error");
		}
		$("#user_ps").keyup(function() {
			if ($("#user_ps").val().length >= 8) {
				$("#pwInputGroup").removeClass("has-error");
				$("#pwInputGroup").addClass("has-success");
			} else {
				$("#pwInputGroup").removeClass("has-success");
				$("#pwInputGroup").addClass("has-error");
			}
		});
	});

})

// 일반 로그인 유효성 검사
function validateLogin() {
	alert("로그인 ajax 함수 호출")
	if ($("#user_id").val() == "") {
		alert("아이디를 입력하세요");
		$("#user_id").focus();
		return;
	}
	if ($("#user_id").val().length < 4) {
		alert("아이디가 4자리 이상이어야 합니다");
		$("#user_id").focus();
		return;
	}

	if ($("#user_ps").val() == "") {
		alert("비밀번호를 입력하세요");
		$("#user_ps").focus();
		return;
	}
	if ($("#user_ps").val().length < 7) {
		alert("비밀번호가 8자리 이상이어야 합니다");
		$("#user_ps").val("");
		$("#user_ps").focus();
		return;
	}

	// 모든 조건 만족시 AJAX 통신으로 form submit
	// AJAX: 비동기식 자바스크립트 XML(JSON)
	// form.serialize(): form 데이터를 string 형식으로 나열
	
	var formData = $("#login").serialize();
	$.ajax({
		url : "loginForm/login",
		type : "POST",
		data : formData,
		success : function(data) {
			if (data != "X") {
				if (data == "이메일 비활성화") {
					alert("이메일 인증이 필요합니다");
					location.href = "loginForm";
				} else {
					alert(data + " 님 안녕하세요");
					location.href = "index";
				}
			} else {
				alert("다시 로그인 해주세요");
				location.href = "loginForm";
			}
		}
	});
}

// 네이버 로그인
function naverLogin() {

}