package com.br.spring.wave.repository;

import com.br.spring.wave.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RepositoryUser extends JpaRepository<Users, Long> {
    //List<User> findByEmail(String email);
    List<Users> findByFistName(String name);
    Users findByPassword(String passoword);

    List<Users> findByLastNameContaining(String name);

    Optional<Users> findByEmail(String email);
}
