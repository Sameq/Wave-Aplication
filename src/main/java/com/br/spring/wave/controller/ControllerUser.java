package com.br.spring.wave.controller;

import com.br.spring.wave.domain.User;
import com.br.spring.wave.dto.EmailDTO;
import com.br.spring.wave.dto.MessageRequestDTO;
import com.br.spring.wave.dto.UserDTO;
import com.br.spring.wave.service.ServiceEmail;
import com.br.spring.wave.service.ServiceUser;
import com.br.spring.wave.service.ServiceWhatsappTwilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Controller
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;

    @Autowired
    ServiceEmail serviceEmail;

    @Autowired
    ServiceWhatsappTwilio serviceWhatsappTwilio;

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody UserDTO user){
        User newUser = this.serviceUser.createUser(user);
        serviceWhatsappTwilio.sendMenssage(newUser.getNumberPhone(), "Seja bem vindo ao Wave");
        serviceEmail.sendEmail(user.email());
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody MessageRequestDTO messageRequest) {
        serviceWhatsappTwilio.sendMenssage(messageRequest.to(), messageRequest.messageBody());
        return "Message sent successfully!";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmailDTO email){
         return ResponseEntity.status(HttpStatus.OK).body("Usuário logado");
    }
}
