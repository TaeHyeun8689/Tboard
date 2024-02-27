package com.springproj.tboard.mail;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.activation.DataSource;

@Component
public class MailHandler {
	private JavaMailSender mailSender;
	private jakarta.mail.internet.MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	
	@Autowired
	public MailHandler(JavaMailSender mailSender) throws jakarta.mail.MessagingException {
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}

	// 이메일 제목
	public void setSubject(String subject) throws jakarta.mail.MessagingException {
		messageHelper.setSubject(subject);
	}

	// 이메일 내용
	public void setText(String htmlContent) throws jakarta.mail.MessagingException {
		messageHelper.setText(htmlContent, true);
	}

	// 보내는 사람 이메일
	public void setFrom(String email, String name) throws UnsupportedEncodingException, jakarta.mail.MessagingException {
		messageHelper.setFrom(email, name);
	}

	// 받는 사람 이메일
	public void setTo(String email) throws jakarta.mail.MessagingException {
		messageHelper.setTo(email);
	}

	public void addInline(String contentId, DataSource dataSource) throws jakarta.mail.MessagingException {
		messageHelper.addInline(contentId, dataSource);
	}

	public void send() {
		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
