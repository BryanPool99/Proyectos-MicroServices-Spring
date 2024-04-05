package com.bryan.Email_Service.service;

import com.bryan.Email_Service.model.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailRequest.getTo());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getBody());

        javaMailSender.send(mailMessage);
    }
}
