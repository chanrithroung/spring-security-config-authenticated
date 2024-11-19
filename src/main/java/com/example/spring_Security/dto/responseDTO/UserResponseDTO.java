package com.example.spring_Security.dto.responseDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
}
