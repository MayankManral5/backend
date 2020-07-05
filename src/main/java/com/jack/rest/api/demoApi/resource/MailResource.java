package com.jack.rest.api.demoApi.resource;

import com.jack.rest.api.demoApi.documents.Email;
import com.jack.rest.api.demoApi.documents.Response;
import com.jack.rest.api.demoApi.documents.Users;
import com.jack.rest.api.demoApi.services.EmailService;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailResource {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email/send-mail")
    public Response sendSimpleMail(@RequestBody Email email){
        return emailService.sendEmail(email);
    }

}
