package com.jack.rest.api.demoApi.services;

import com.jack.rest.api.demoApi.documents.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
        
    }


    public void sendEmail(Users users) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(users.getEmail());
        mail.setSubject("Testing Mail API");
        mail.setText("Hello This is a test mail to check mailing service");

        javaMailSender.send(mail);
    }

}
