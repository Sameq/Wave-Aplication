package com.br.spring.wave.domain;

import com.br.spring.wave.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Builder
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Users {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String fistName;

    @NotBlank
    @Size(max = 60)
    private String lastName;

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String numberPhone;

    @NotBlank
    @Size(max = 255)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Users(UserDTO user) {
        this.fistName = user.fistName();
        this.lastName = user.lastName();
        this.numberPhone = user.numberPhone();
        this.email = user.email();
        this.password = user.password();
        this.userType = user.userType();
    }


}
