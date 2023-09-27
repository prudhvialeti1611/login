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
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class SignupController {
    private final UserRepository userRepository;

    @Autowired
    public SignupController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {

        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }


        user.setRegistrationDate(LocalDate.now());


        userRepository.save(user);


        return ResponseEntity.ok("User registered successfully");
    }
}
