package com.br.spring.wave.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Senha ou email inválidos");
    }

}
