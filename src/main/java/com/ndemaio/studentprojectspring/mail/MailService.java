package com.ndemaio.studentprojectspring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String emailTo, String message) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(emailTo);
        email.setText(message);
        email.setFrom("studentprojectspring@gmail.com"); // Correo para envio automatico

        mailSender.send(email);
    }
}
