package com.jack.rest.api.demoApi.resource;

import com.jack.rest.api.demoApi.documents.Users;
import com.jack.rest.api.demoApi.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailResource {
    @Autowired
    private MailService notificationService;
    private Users user;
    @RequestMapping("/send-mail")
    public String send() {

        /*
         * Creating a User with the help of User class that we have declared and setting
         * Email address of the sender.
         */
        user.setEmail("manral.mayank7@gmail.com");  //Receiver's email address
        /*
         * Here we will call sendEmail() for Sending mail to the sender.
         */
        try {
            notificationService.sendEmail(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

}
