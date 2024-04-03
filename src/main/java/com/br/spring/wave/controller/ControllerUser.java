package com.br.spring.wave.controller;

import com.br.spring.wave.domain.User;
import com.br.spring.wave.dto.EmailDTO;
import com.br.spring.wave.dto.MessageRequestDTO;
import com.br.spring.wave.dto.UserDTO;
import com.br.spring.wave.exception.NegocioException;
import com.br.spring.wave.repository.RepositoryUser;
import com.br.spring.wave.service.ServiceCadastroUser;
import com.br.spring.wave.service.ServiceEmail;
import com.br.spring.wave.service.ServiceUser;
import com.br.spring.wave.service.ServiceWhatsappTwilio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Controller
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;

    @Autowired
    ServiceEmail serviceEmail;

    @Autowired
    ServiceWhatsappTwilio serviceWhatsappTwilio;

    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    ServiceCadastroUser serviceCadastroUser;

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

    @GetMapping("/listar")
    public List<User> listar(){
        return repositoryUser.findAll();
    } //buscar todos os users

    @GetMapping("/buscarNome")
    public List<User> buscarFistName(){
        return repositoryUser.findByFistName("Maria");
    } //buscar por user com esse primeiro nome

    @GetMapping("/buscarNomeContendo")
    public List<User> buscarNomeContendo(){
        return repositoryUser.findByLastNameContaining("Nova");
    } //buscar por users contedo esse nome

    @GetMapping("/{userId}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long userId){
        Optional<User> user = repositoryUser.findById(userId);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    } //buacar user por id

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User adicionar(@Valid @RequestBody User user){
        return serviceCadastroUser.salvar(user);
    } //adicionar um user

    @PutMapping("/{userId}")
    public ResponseEntity<User> atualizar(@PathVariable Long userId,
                                          @Valid @RequestBody User user){
        if(!repositoryUser.existsById(userId)){
            return ResponseEntity.notFound().build();
        }

        user.setId(userId);
        user = serviceCadastroUser.salvar(user);

        return ResponseEntity.ok(user);
    } //editar dados do clinte pelo id

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> excluir(@PathVariable Long userId){
        if(!repositoryUser.existsById(userId)){
            return ResponseEntity.notFound().build();
        }

        serviceCadastroUser.excluirUser(userId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturarExeção(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
