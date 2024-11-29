package com.example.spring_Security.dto.responseDTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
}
