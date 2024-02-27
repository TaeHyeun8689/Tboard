package com.springproj.tboard.domain;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BoardVO")
public class BoardVO {
	private int board_num;
	private String board_title;
	private String board_content;
	private String board_writer;
	private int board_hit;
	private int board_recommend;
	private Timestamp board_reg_date;
	private int board_reply_count;

}