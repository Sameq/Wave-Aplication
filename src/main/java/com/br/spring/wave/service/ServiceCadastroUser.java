package com.br.spring.wave.service;

import com.br.spring.wave.domain.User;
import com.br.spring.wave.exception.NegocioException;
import com.br.spring.wave.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ServiceCadastroUser {

    RepositoryUser repositoryUser;

    public User buscarPorId(Long userId){
        return repositoryUser.findById(userId)
                .orElseThrow(() -> new NegocioException("User não encontrado"));
    }

    @Transactional
    public User salvar(User user){
        //verifica se email ja existe
        boolean emailEmUso = repositoryUser.findByEmail(user.getEmail())
                .filter(u -> !u.equals(user))
                .isPresent();

        //lança a exeção
        if(emailEmUso){
            throw new NegocioException("Já existe um user cadastrado com esse email");
        }

        return repositoryUser.save(user);
        //se tude estiver certo o user sera salvo

    } //regra de negoçio de (adicionar/salvar) user

    public void excluirUser(Long userId){
        repositoryUser.deleteById(userId);
    }
}
