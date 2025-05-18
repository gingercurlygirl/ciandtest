package com.example.ciandtest.controller;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.model.User;
import com.example.ciandtest.model.UserDTO;
import com.example.ciandtest.service.TodoService;
import com.example.ciandtest.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        Optional<UserDTO> exists = userService.findByUsername(user.getUsername());
        if (exists.isEmpty()) {
            return ResponseEntity.ok(userService.addUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Todo> createTodoForUser(@PathVariable Long userId, @Valid @RequestBody Todo todo) {
        Optional<UserDTO> exists = userService.findById(userId);
        if (exists.isPresent()) {
            todo.setUser(new User(exists.get()));
            return ResponseEntity.ok(todoService.addTodo(todo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.of(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long userId) {
        Optional<UserDTO> exists = userService.findById(userId);
        if (exists.isPresent()) {
            return ResponseEntity.ok(userService.deleteUser(exists.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<UserDTO> exists = userService.findById(userId);
        if (exists.isPresent()) {
            UserDTO userToUpdate = exists.get();

            if (user.getAge() != null) userToUpdate.setAge(user.getAge());
            if (user.getUsername() != null) userToUpdate.setUsername(user.getUsername());

            return ResponseEntity.ok(userService.updateUser(userToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
