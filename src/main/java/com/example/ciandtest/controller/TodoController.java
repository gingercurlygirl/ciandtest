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


@RequestMapping("/todos")
@RestController
public class TodoController {

    UserService userService;
    TodoService todoService;

    public TodoController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Todo> createTodoForUser(@PathVariable Long userId, @Valid @RequestBody Todo todo) {
        Optional<UserDTO> exists = userService.findById(userId);
        if (exists.isPresent()) {
            todo.setUser(new User(exists.get()));
            return ResponseEntity.ok(todoService.addTodo(todo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Todo>> getUserTodos(@PathVariable Long userId) {
        return ResponseEntity.ok(todoService.getTodos(userId));
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable Long todoId) {
        Optional<Todo> exists = todoService.findById(todoId);
        if (exists.isPresent()) {
            return ResponseEntity.ok(todoService.deleteTodo(exists.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo todo) {
        Optional<Todo> exists = todoService.findById(todoId);
        if (exists.isPresent()) {
            Todo todoToUpdate = exists.get();

            if (todo.getTask() != null) todoToUpdate.setTask(todo.getTask());
            if (todo.isCompleted() != null) todoToUpdate.setCompleted(todo.isCompleted());

            return ResponseEntity.ok(todoService.updateTodo(todoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
