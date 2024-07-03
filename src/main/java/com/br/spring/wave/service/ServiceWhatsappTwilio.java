package com.br.spring.wave.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceWhatsappTwilio {
    //@Value("${twilio.accountSid}")
    private String accountSid;

    //@Value("${twilio.authToken}")
    private String authToken;

    //@Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public void sendMenssage(String to, String messageBody){
        Twilio.init(accountSid,authToken);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5574999177175"),//poe seu numero aqui
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),// numero twilio teste
                messageBody
        ).create();
        System.out.println("Message sent successfully. SID: " + message.getSid());
    }
}
