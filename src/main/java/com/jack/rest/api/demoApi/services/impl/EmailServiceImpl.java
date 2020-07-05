package com.jack.rest.api.demoApi.services.impl;

import com.jack.rest.api.demoApi.documents.Email;
import com.jack.rest.api.demoApi.documents.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements com.jack.rest.api.demoApi.services.EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Response sendEmail(Email mail) {
        Response response = new Response();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setText(mail.getText());
            emailSender.send(message);
            response.setCode(0);
            response.setMessage("Email send!!! Okay");
        }catch(Exception ex){
            response.setCode(1);
            response.setMessage("Error While sending mail: "+ex.getMessage());
        }

        return response;
    }
}
