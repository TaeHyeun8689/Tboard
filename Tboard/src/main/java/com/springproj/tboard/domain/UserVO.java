package com.springproj.tboard.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserVO {
	private String user_id;
	private String user_ps;
	private String user_name;
	private String user_sex;
	private String user_email;
	private String user_e_active_key;
	private String user_phone; 
	private int user_birthday;
	private int user_active_state;
	private Timestamp user_reg_date;
	private Timestamp user_latest_login;
	private Timestamp user_recommend_active;
	
}
