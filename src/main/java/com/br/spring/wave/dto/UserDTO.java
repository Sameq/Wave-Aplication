package com.br.spring.wave.dto;

import com.br.spring.wave.domain.UserType;

public record UserDTO(String fistName, String lastName,String numberPhone, String email, String password, UserType userType) {
}
