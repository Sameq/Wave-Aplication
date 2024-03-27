package com.br.spring.wave.exception;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(){
        super("Email já cadastrado.");
    }
}
