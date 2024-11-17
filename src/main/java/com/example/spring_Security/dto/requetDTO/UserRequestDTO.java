package com.example.spring_Security.dto.requetDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private Long id;
    private String username;
    private String password;
}
