package com.example.ciandtest.controller;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.service.TodoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/todos")
@RestController
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Todo>> getUserTodos(@PathVariable Long userId) {
        return ResponseEntity.ok(todoService.getTodos(userId));
    }
}
