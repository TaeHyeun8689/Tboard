댓글 게시판 - 개인 프로젝트

목적 : 신입 웹 개발자의 기본소양 반복된 연습을 통해 실력 향상에 포커스

개발환경 : Spring Boot, Java, Javascript, Jsp, html/css, ajax, Mybatis, Oracle DB, ErdCloud

제작기간 - 2024/02/13 ~ 2024/02/27 (작업 기간 : 14일)

1일차 : 프로젝트 주제 구상 생성 및 ERD CLOUD 구현, DB TABLE 생성

2일차 ~ 5일차 : 기본 View단 완성

5일차 ~ 10일차 : 기본 게시판 crud 회원가입 아이디 중복쳌, , 이메일 인증 구현

11일차 ~ 13일차 : 댓글 crud 추천 및 조회수 기능 구현

13일차~ 14일차 : 디버깅 및 마무리



Tboard 데이터베이스 erd cloud: https://www.erdcloud.com/d/KtctXWcpvhPBrHpJ3


Front-End 주요 기능

1. 회원가입 및 로그인시 유효성 검사를 css클래스 추가 및 제거로 가시적으로 표현
![로그인 유효성 검사](https://github.com/TaeHyeun8689/Tboard/assets/159877124/0ba1a98e-fb34-4959-b6ac-a212639124fa)



2. 인증 메일 전송시 애니메이션 구현
![회원가입 이메일 애니매이션](https://github.com/TaeHyeun8689/Tboard/assets/159877124/6b39cbfb-42b2-46e8-b599-deacf307f67d)



3. 게시판 글쓰기, 글 수정 네이버 에디터2 적용
![네이버 에디터2 0](https://github.com/TaeHyeun8689/Tboard/assets/159877124/77d463bb-986e-4a30-ba94-9c70562b793d)



4. include를 이용하여 헤더 및 푸터 반복코드 제거
![include폴더](https://github.com/TaeHyeun8689/Tboard/assets/159877124/891bad91-f526-404b-9bfe-c94975888758)



Back-End 주요 기능

1. MailAuthKeyGenerator 인증키 생성
![mail인증키 생성](https://github.com/TaeHyeun8689/Tboard/assets/159877124/1f204634-ece3-4c0d-82cb-1e7f2543a751)




2. MailHandler 메일 전송
![메일생성](https://github.com/TaeHyeun8689/Tboard/assets/159877124/44033ddf-131b-4e33-b7f0-295550657b3d)




3. PageCriteria 게시판 페이징 기준
![페이징기능](https://github.com/TaeHyeun8689/Tboard/assets/159877124/5c261777-b4ba-41c0-8dee-0f20be5671c0)




4. PageMaker 게시판 페이징

