package com.example.ciandtest.controller;

import com.example.ciandtest.model.User;
import com.example.ciandtest.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/users")
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        Optional<User> exists = userService.findByUsername(user.getUsername());
        if (exists.isEmpty()) {
            return ResponseEntity.ok(userService.addUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
