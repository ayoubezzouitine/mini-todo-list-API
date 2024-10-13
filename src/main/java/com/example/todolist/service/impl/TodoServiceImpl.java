package com.example.todolist.service.impl;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(long id) throws Exception {
        Optional<Todo> todoById = getTodoById(id);
        Todo todo = todoById.orElseThrow(() -> new Exception("Todo with id " + id + " not found"));
        // Proceed with deleting the todo
        todoRepository.delete(todo);
    }
}
