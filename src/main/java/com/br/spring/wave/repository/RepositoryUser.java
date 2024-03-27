package com.br.spring.wave.repository;

import com.br.spring.wave.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFistName(String name);
    User findByPassword(String passoword);
}
