package com.br.spring.wave.domain;

import com.br.spring.wave.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fistName;
    private String lastName;
    @Column(unique = true)
    private String numberPhone;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO user) {
        this.fistName = user.fistName();
        this.lastName = user.lastName();
        this.numberPhone = user.numberPhone();
        this.email = user.email();
        this.password = user.password();
        this.userType = user.userType();
    }
}
