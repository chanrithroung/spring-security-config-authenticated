package com.example.spring_Security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index(@RequestParam( value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }


    @GetMapping("/greet")
    public String greet(HttpServletRequest request) {
        return "JSESSIONID : " + request.getSession().getId();
    }
}
