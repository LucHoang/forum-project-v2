package com.casestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/forgotPassword")
    public String form() {
        return "/views/forgot-password";
    }

    @ResponseBody
    @RequestMapping("/sendEmail")
    public String sendSimpleEmail(@RequestParam("email") String email) {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

//        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setTo(email);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}