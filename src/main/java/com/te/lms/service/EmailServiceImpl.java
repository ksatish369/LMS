package com.te.lms.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.te.lms.entity.EmailDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;

	@Override
	public boolean sendMail(EmailDetails emailDetails) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailDetails.getSender());
		simpleMailMessage.setTo(emailDetails.getReciever());
		simpleMailMessage.setSubject(emailDetails.getSubject());
		simpleMailMessage.setText(emailDetails.getMessageBody());
		javaMailSender.send(simpleMailMessage);
		return true;
	}

	public boolean sendMailWithAttachment(EmailDetails emailDetails) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		mimeMessageHelper.setTo(emailDetails.getReciever());
		mimeMessageHelper.setText(emailDetails.getMessageBody());
		mimeMessageHelper.setSubject(emailDetails.getSubject());
		
		FileSystemResource fileSystemResource = new FileSystemResource(new File(emailDetails.getAttachment()));	
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javaMailSender.send(mimeMessage);
		return true;
	}

}
