package com.snapdeal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.snapdeal.component.MailDetails;

@Service
public class MailService implements Runnable{
	
	@Autowired
	JavaMailSender mailSender;
	
	private MailDetails mailDetails;
	
	@Override
	public void run() {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mailDetails.getRecipients());
		email.setSubject(mailDetails.getSubject());
		email.setText(mailDetails.getBody());
		email.setFrom(mailDetails.getSender());
	
		mailSender.send(email);
	}

	public void setMailDetails(MailDetails mailDetails) {
		this.mailDetails = mailDetails;
	}

	public MailDetails getMailDetails() {
		return mailDetails;
	}

}
