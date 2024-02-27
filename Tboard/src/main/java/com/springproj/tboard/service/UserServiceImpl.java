package com.springproj.tboard.service;

import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproj.tboard.domain.UserVO;
import com.springproj.tboard.repository.UserDAO;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public int idDupCheck(String checkId) throws Exception {
		int check = -2;
		check = userDao.inDupCheck(checkId);
		return check;
	}
	
	@Override
	public void signUp(HashMap<String, Object> params) throws Exception {
		userDao.signUp(params);
	}
	
	@Override
	public int activateEmail(HashMap<String, Object> params) throws Exception {
		return userDao.activateEmail(params);
	}
	
	@Override
	public UserVO login(HashMap<String, Object> params) throws Exception {

		return userDao.login(params);
	}
	
	@Override
	public void loginLatestTimeUpdate(HashMap<String, Object> params) throws Exception {

		userDao.loginLatestTimeUpdate(params);
	}
	
	@Override
	public void updateRecommendActiveTime(String user_id) throws Exception {

		userDao.updateRecommendActiveTime(user_id);
	}
	
	@Override
	public Timestamp checkRecommendActiveTime(String user_id) throws Exception {

		return userDao.checkRecommendActiveTime(user_id);
	}
}