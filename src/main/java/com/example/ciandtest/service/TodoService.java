package com.example.ciandtest.service;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.repository.TodoRepository;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    TodoRepository todoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo addTodo(Todo todo) {
        entityManager.clear(); // TODO: testiraj kasnije jel ovo potrebno
        LocalDate now = LocalDate.now();
        todo.setCreate_date(Date.valueOf(now));
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodos(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo deleteTodo(Todo todo) {
        todoRepository.delete(todo);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

}
