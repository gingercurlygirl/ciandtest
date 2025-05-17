package com.example.ciandtest.controller;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.model.User;
import com.example.ciandtest.service.TodoService;
import com.example.ciandtest.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequestMapping("/users")
@RestController
public class UserController {

    UserService userService;
    TodoService todoService;

    public UserController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        Optional<User> exists = userService.findByUsername(user.getUsername());
        if (exists.isEmpty()) {
            return ResponseEntity.ok(userService.addUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Todo> createTodoForUser(@PathVariable Long userId, @Valid @RequestBody Todo todo) {
        Optional<User> exists = userService.findById(userId);
        if (exists.isPresent()) {
            todo.setUser(exists.get());
            return ResponseEntity.ok(todoService.addTodo(todo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.of(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long userId) {
        Optional<User> exists = userService.findById(userId);
        if (exists.isPresent()) {
            return ResponseEntity.ok(userService.deleteUser(exists.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> exists = userService.findById(userId);
        if (exists.isPresent()) {
            User userToUpdate = exists.get();

            if (user.getAge() != null && !Objects.equals(user.getAge(), userToUpdate.getAge())) userToUpdate.setAge(user.getAge());
            if (user.getUsername() != null && !user.getUsername().equals(userToUpdate.getUsername())) userToUpdate.setUsername(user.getUsername());

            return ResponseEntity.ok(userService.updateUser(userToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
