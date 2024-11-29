package com.example.spring_Security.controller.userController;


import com.example.spring_Security.dto.requetDTO.UserRequestDTO;
import com.example.spring_Security.dto.responseDTO.UserResponseDTO;
import com.example.spring_Security.service.userServices.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController
//@RequestMapping("/user")
public class UserControllers {
    private final UserService userService;

    @Autowired
    public UserControllers(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> register(@RequestBody UserRequestDTO userRequestDTO) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status",200);
        response.put("message", "User register success");
        response.put("payload", userService.registerUser(userRequestDTO));
        return ResponseEntity.ok(response);

    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserRequestDTO requestDTO) {
        return userService.verify(requestDTO);
    }

}
