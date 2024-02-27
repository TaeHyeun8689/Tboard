package com.springproj.tboard.repository;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.springproj.tboard.domain.UserVO;

@Mapper
public interface UserDAO {
	
	//아이디 중복 확인을 위한 메서드
	public int inDupCheck(String checkID) throws Exception;
	
	//회원 메일에서 아이디 활성화 요청을 위한 메서드
	public int activateEmail(HashMap<String, Object> params) throws Exception;
	
	//회원가입 메서드
	public void signUp(HashMap<String, Object> params) throws Exception;
	
	//회원 로그인 메서드
	public UserVO login(HashMap<String, Object> params) throws Exception;
	
	//최근 로그인 시간 업데이트
	public void loginLatestTimeUpdate(HashMap<String, Object> params) throws Exception;
	
	// 추천 후, 추천 활성화 시간 업데이트
	public void updateRecommendActiveTime(String user_id) throws Exception;
	
	// 추천 활성화 시간 조회
	public Timestamp checkRecommendActiveTime(String user_id) throws Exception;
	
}
