package com.br.spring.wave.repository;

import com.br.spring.wave.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RepositoryUser extends JpaRepository<User, Long> {
    //List<User> findByEmail(String email);
    List<User> findByFistName(String name);
    User findByPassword(String passoword);

    List<User> findByLastNameContaining(String name);

    Optional<User> findByEmail(String email);
}
