package com.br.spring.wave.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String email;
    private String password;
    private Long id;
    private String token;
}
