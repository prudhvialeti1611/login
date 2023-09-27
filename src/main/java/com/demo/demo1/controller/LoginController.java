package com.demo.demo1.controller;

import com.demo.demo1.entity.User;
import com.demo.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {

        User storedUser = userRepository.findByUsername(user.getUsername());

        if (storedUser != null) {

            if (storedUser.getPassword().equals(user.getPassword())) {

                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                return ResponseEntity.ok(response);
            } else {

                Map<String, String> response = new HashMap<>();
                response.put("message", "Login failed: Invalid password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login failed: User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
