package com.br.spring.wave.infra;

import com.br.spring.wave.exception.InvalidCredentialsException;
import com.br.spring.wave.exception.UserAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@Repository
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<String> userAlreadyRegisteredHandler(UserAlreadyRegisteredException exceptionUser){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado!");
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> userInvalidCredntialHandler(InvalidCredentialsException invalidCredentials){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha ou email incorretos!");
    }
}
