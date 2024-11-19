package com.example.spring_Security.mapper;

import com.example.spring_Security.dto.requetDTO.UserRequestDTO;
import com.example.spring_Security.dto.responseDTO.UserResponseDTO;
import com.example.spring_Security.model.UserModel.UserModel;

public class UserMapper {
    public static UserModel toEntity(UserRequestDTO requestDTO) {
        UserModel userModel = new UserModel();
        userModel.setId(requestDTO.getId());
        userModel.setUsername(requestDTO.getUsername());
        userModel.setPassword(requestDTO.getPassword());
        return userModel;
    }

    public static UserResponseDTO toDTO(UserModel userModel) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userModel.getId());
        userResponseDTO.setUsername(userModel.getUsername());
        userResponseDTO.setPassword(userModel.getPassword());
        return userResponseDTO;
    }
}
