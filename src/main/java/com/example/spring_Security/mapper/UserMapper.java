package com.example.spring_Security.mapper;

import com.example.spring_Security.dto.requetDTO.UserRequestDTO;
import com.example.spring_Security.model.UserModel.UserModel;
import org.apache.catalina.User;

public class UserMapper {
    public UserModel toEntity(UserRequestDTO requestDTO) {
        UserModel userModel = new UserModel();
        userModel.setId(requestDTO.getId());
        userModel.setUsername(requestDTO.getUsername());
        userModel.setPassword(requestDTO.getPassword());
        return userModel;
    }
}
