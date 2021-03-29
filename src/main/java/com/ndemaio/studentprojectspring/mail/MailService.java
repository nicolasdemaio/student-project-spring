package com.ndemaio.studentprojectspring.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender mailSender;

    public void sendMail(String emailTo, String message) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(emailTo);
        email.setText(message);
        email.setFrom("nicolas.demaio19@gmail.com");

        mailSender.send(email);
    }
}
