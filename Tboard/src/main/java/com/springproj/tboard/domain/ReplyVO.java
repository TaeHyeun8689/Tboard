package com.springproj.tboard.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyVO {
	private int reply_num;
	private int board_num;
	private String reply_id;
	private String reply_content;
	private Timestamp reply_reg_date;
	
}
