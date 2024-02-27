package com.springproj.tboard.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproj.tboard.client.ClientIP;
import com.springproj.tboard.domain.UserVO;
import com.springproj.tboard.mail.MailAuthKeyGenerator;
import com.springproj.tboard.mail.MailHandler;
import com.springproj.tboard.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	
	/*Inject 와 Autowired의 차이점
	 *  두 어노테이션은 의존성 주입을 위한 기능을 제공 약간 차이가 있음.
	 *  Autowired는 Spring 고유 어노테이션 타입,필드,생성자, 메소드의 매개변수에 사용할 수 있음 자동 주입
	 *  Inject는 JSR-330 스펙의 일부 JavaEE 에서 정의된 어노테이션 타입에 기반하여 의존성을 주입 컨텍스트 안에서 타입의 빈을 찾아주어야 함.
	 * */
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserService userService;
	

	// 회원가입 페이지 이동 컨트롤러
	@RequestMapping("/signUpForm")
	public String MoveSignUpForm() {
		return "signUpForm";
	}
	
	//아이디 중복 체크 요청
	//paramId에 담기는 파라미터 id
	@RequestMapping(value = "signUpForm/idDupCheck", produces = "application/text; charset=utf8")
	public @ResponseBody String responseIdDupCheck(@RequestParam String paramId) throws Exception {
		int result = -1;
		String checkedId = "";
		// 검색 후, 0이면 사용가능, 1이면 중복, -1이면 메서드 작동X, -2이면 SQL 작동X
		result = userService.idDupCheck(paramId);
		if (result == 0) {
			checkedId = paramId;
		} else if (result == 1) {
			checkedId = "";
		} else {
			checkedId = "";
		}
		return checkedId;
	}
	//이메일 인증 요청
	// paramEmail에 담기는 파라미터 email
	// 발송 딜레이 고려하여 회원가입 버튼 누르기
	
	@RequestMapping("signUpForm/emailCertify")
	public @ResponseBody String responseEmailCertify(@RequestParam String paramEmail) throws Exception {
		System.out.println("인증메일 전송 주소: " + paramEmail);

		// 인증 키 생성
		String random_24_string = new MailAuthKeyGenerator().excuteGenerate();
		System.out.println("active_key 값: " + random_24_string);

		// 인증 키 유저에게 메일 보내기(get)
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[ Tboard 회원가입 이메일 인증 메일입니다 ]");
		sendMail.setText(new StringBuffer().append("<h1>이메일 인증을 진행해주세요</h1>").append("<h1><a href='http://localhost:8090/tboard/emailActivate?user_email=")
				.append(paramEmail).append("&user_e_active_key=").append(random_24_string).append("' target='_blank'>이메일 인증 링크입니다</a></h1>").toString());
		sendMail.setFrom("xogus8689@gmail.com", "Tboad 운영자");
		sendMail.setTo(paramEmail);
		sendMail.send();

		return random_24_string;
	}
	
	//이메일 활성화 요청
	//params에 담기는 파라미터 : email, active_key
	//이메일 인증 후, active_key 값 "0" 으로 수정
	@RequestMapping("tboard/emailActivate")
	public String responseEmailActivate(@RequestParam HashMap<String , Object> params) throws Exception{
		
		int result = -1;
		System.out.println("이메일 활성화 매서드 실행");
		result = userService.activateEmail(params);
		
		if(result == 1) {
			System.out.println("아이디 이용이 가능합니다.");
			//JOptionPane.showMessageDialog(null, "아이디 이용이 가능합니다");
		} else {
			System.out.println("이메일 인증 키가 소멸되었습니다.");
			//JOptionPane.showMessageDialog(null, "이메일 인증 키가 소멸되었습니다");
		}
		return "redirect:/";
		}
	
	//회원가입 요청
	//params에 담기는 파라미터 : id ps name sex birthday email
	//auth_key(hidden) phone
	
	@RequestMapping("signUpForm/signUp")
	public String responseSignUp(@RequestParam HashMap<String, Object> params) throws Exception{
		
		userService.signUp(params);
		
		//java.awt.HeadlessException은 그래픽 환경이 없는 환경에서 그래픽 작업을 시도할 때 발생합니다.
		//이러한 환경에서는 JOptionPane와 같은 그래픽 관련 클래스를 사용할 수 없습니다. 
		//JOptionPane.showMessageDialog(null, "회원 가입을 축하합니다.");
		System.out.println("회원 가입을 축하합니다.");
		
		return "redirect:/login";
	}
	
	// 로그인 페이지 이동 컨트롤러
	@RequestMapping("/loginForm")
	public String MoveLoginForm() {
		return "loginForm";
	}
	
	//로그인 요청
	//params에 담기는 파라미터 :user_id,user_ps
	@RequestMapping(value = "loginForm/login", produces = "application/text; charset=utf8")
	public @ResponseBody String jsonLogin(@RequestParam HashMap<String, Object> params, HttpSession session, HttpServletRequest request) throws Exception{
		String result = "X";
		if (session.getAttribute("login_session") != null) {
			session.removeAttribute("login_session");
		}
		//로그인 시도
		UserVO vo = userService.login(params);
		System.out.println(vo);
		//로그인 성공시
		if(vo != null) {
			if(vo.getUser_active_state() == 1) {
				System.out.println("로그인 성공 후 세션 저장 if문 진입");
				userService.loginLatestTimeUpdate(params);
				
				session.setAttribute("login_session", vo);
				
				result = vo.getUser_id();
			}else {
				result = "이메일 비활성화";
			}System.out.println(result);
		}
		// 로그인 유저 아이피 가져오기
				ClientIP cipAddr = new ClientIP();
				System.out.println("Login Client IP Address: " + cipAddr.getClientIPAddr(request));
				System.out.println("Session Valid Time: " + (session.getMaxInactiveInterval()+" seconds" ));
				// "X", "아이디", "이메일 비활성화" 3가지 경우의 수 반환
				return result;
	}
	
	//로그아웃
	@RequestMapping("logOut")
	public String LogOut(HttpSession session) {
		
		//세션 삭제
		session.invalidate();
		
		return "redirect:index";
	}
}
