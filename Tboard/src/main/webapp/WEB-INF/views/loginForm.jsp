<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Taehyeun board page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 커스텀 CSS -->
<link href="resources/css/loginStyle.css" rel="stylesheet" type="text/css" media="screen">
<!-- 로그인 유효성 검사 JS -->
<script type="text/javascript" src="resources/js/loginValidation1.js"></script>
</head>

<body>
	<!-- 최상단 네비 -->
	<div class="container-fluid">
		<nav class="navbar navbar-default">
			<ul class="nav navbar-nav navbar-right">
				<li style="font-size: 16px"><a href="index">메인으로</a></li>
			</ul>
		</nav>
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<a href="index"><img src="resources/img/t_favicon.png" class="img-thumbnail" width="100px" height="100px"></a>
			</div>
		</nav>
	</div>

	<div class="container-fluid text-center">
		<div id="loginWrap" class="row">
			<div class="col-sm-8">
				<!-- 로그인 폼-->
				<!-- ajax로 form 제출시 action 경로 생략 -->
				<form id="login" method="post">
					<!-- 아이디 -->
					<div id="idInputGroup" class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input id="user_id" type="text" class="form-control" name="user_id" placeholder="4자리 이상 영문 아이디를 입력하세요">
					</div>
					<!-- 비밀번호 -->
					<div id="pwInputGroup" class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-lock"></i>
						</span>
						<input id="user_ps" type="password" class="form-control" name="user_ps" placeholder="8자리 이상 비밀번호를 입력하세요">
					</div>
					<button onclick="validateLogin();return false;" class="btn btn-primary btn-block">로그인</button>
				</form>
				<button id="naverLogin" onclick="naverLogin()" class="btn btn-block">NAVER 로그인</button>
				<!-- 로그인 옵션 -->
				<div id="loginOption">
					<a href="#">아이디 찾기</a>
					<span class="bar">|</span>
					<a href="#">비밀번호 찾기</a>
					<span class="bar">|</span>
					<a href="signUpForm">회원가입</a>
				</div>
			</div>
			<div id="sideAds" class="col-sm-4">
				<div class="well">
					<p>광고 1</p>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="include/footer.jsp" />
</body>

</html>