package com.br.spring.wave.service;

import com.br.spring.wave.domain.User;
import com.br.spring.wave.dto.LoginDTO;
import com.br.spring.wave.dto.UserDTO;
import com.br.spring.wave.exception.InvalidCredentialsException;
import com.br.spring.wave.exception.UserAlreadyRegisteredException;
import com.br.spring.wave.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {
    @Autowired
    RepositoryUser repositoryUser;
    public User createUser(UserDTO userDTO){
        if(repositoryUser.findByEmail(userDTO.email()) != null){
            throw new UserAlreadyRegisteredException();
        }
        User newUser = new User(userDTO);
        this.saveUSer(newUser);
        return newUser;
    }
    public void saveUSer(User data){
        this.repositoryUser.save(data);
    }

    public Boolean checkLogin(LoginDTO checkLogin){
        if (this.repositoryUser.findByEmail(checkLogin.email()) == null){
//            || this.repositoryUser.findByPassword(checkLogin.password()) == null){
            return false;
        }
        return true;
    }

    public Boolean login(LoginDTO login){
        if (!checkLogin(login)){
            throw new InvalidCredentialsException();
        }
        return true;
    }
}
