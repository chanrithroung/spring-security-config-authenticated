package com.example.spring_Security.service.userServices;

import com.example.spring_Security.dto.requetDTO.UserRequestDTO;
import com.example.spring_Security.mapper.UserMapper;
import com.example.spring_Security.model.UserModel.UserModel;
import com.example.spring_Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(UserRequestDTO userDTO) {
        UserModel userModel = UserMapper.toEntity(userDTO);
        return userRepository.save(userModel);
    }

}
