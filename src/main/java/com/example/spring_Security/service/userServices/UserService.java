package com.example.spring_Security.service.userServices;

import com.example.spring_Security.dto.requetDTO.UserRequestDTO;
import com.example.spring_Security.mapper.UserMapper;
import com.example.spring_Security.model.UserModel.UserModel;
import com.example.spring_Security.repository.UserRepository;
import com.example.spring_Security.service.TokenService.JWTServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTServices jwtServices;


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JWTServices jwtServices) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtServices = jwtServices;
    }

    public UserModel registerUser(UserRequestDTO userDTO) {
        UserModel userModel = UserMapper.toEntity(userDTO);
        return userRepository.save(userModel);
    }

    public ResponseEntity<Object> verify(UserRequestDTO userRequestDTO) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDTO.getUsername(),
                            userRequestDTO.getPassword()
                    ));

            // check if authenticate is successful
            if (authentication.isAuthenticated()) {
                HashMap<String, Object> response = new HashMap<>();
                response.put("message", "success");
                String token = jwtServices.generateToken(userRequestDTO.getUsername());
                System.out.println(token);
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                // Handle unexpected authentication failures (this block might rarely be reached)
                return ResponseEntity.status(401).body(Map.of("message", "Authentication Fail"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password", "error", e.getMessage()));
        }
    }

}
