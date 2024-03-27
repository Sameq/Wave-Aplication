package com.br.spring.wave.service;

import com.br.spring.wave.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmail {
    private final JavaMailSender mailSender;
    public ServiceEmail(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    public void sendEmail(String email){
        var message = new SimpleMailMessage();
        message.setFrom("");// seu email
        message.setTo(email);
        message.setSubject("Teste API");
        message.setText("Bem vindo ao Wave!!");
        mailSender.send(message);
    }
}
