package com.br.spring.wave.service;

import com.br.spring.wave.domain.Users;
import com.br.spring.wave.dto.AuthDto;
import com.br.spring.wave.dto.LoginDTO;
import com.br.spring.wave.dto.UserDTO;
import com.br.spring.wave.exception.InvalidCredentialsException;
import com.br.spring.wave.exception.UserAlreadyRegisteredException;
import com.br.spring.wave.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@Service
public class ServiceUser implements UserDetailsService {
    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createUser(Users user){
        /*if(repositoryUser.findByEmail(user.getEmail()) != null){
            throw new UserAlreadyRegisteredException();
        }*/

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.saveUser(user);
        return user;
    }

    public void saveUser(Users data){
        this.repositoryUser.save(data);
    }

    public Boolean checkLogin(LoginDTO checkLogin){
        if (this.repositoryUser.findByEmail(checkLogin.email()) == null){
            return false;
        }
        return true;
    }

    public AuthDto auth(AuthDto authDto) {
        Users users = this.findByEmail(authDto.getEmail());

        if (!this.passwordEncoder.matches(authDto.getPassword(), users.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        StringBuilder password = new StringBuilder().append(users.getEmail()).append(":").append(users.getPassword());

        return AuthDto.builder().email(users.getEmail()).token(
                Base64.getEncoder().withoutPadding().encodeToString(password.toString().getBytes())
        ).id(users.getId()).build();
    }

    private Users findByEmail(String email) {
        return repositoryUser.findByEmail(email).orElseThrow(
                () -> new RuntimeException(String.format("Usuário email=%s não encontrado", email)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = this.repositoryUser.findByEmail(username);

        return usersOptional.map(users -> new User(users.getEmail(), users.getPassword(), new ArrayList<GrantedAuthority>()))
                .orElseThrow(() -> new RuntimeException("User not found" + username));
    }

    public Boolean login(LoginDTO login){
        if (!checkLogin(login)){
            throw new InvalidCredentialsException();
        }
        return true;
    }
}
